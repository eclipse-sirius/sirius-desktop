/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.aql.business.internal;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.eclipse.acceleo.query.ast.Expression;
import org.eclipse.acceleo.query.parser.AstEvaluator;
import org.eclipse.acceleo.query.runtime.AcceleoQueryEvaluationException;
import org.eclipse.acceleo.query.runtime.AcceleoQueryValidationException;
import org.eclipse.acceleo.query.runtime.CrossReferenceProvider;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationMessage;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.InvalidAcceleoPackageException;
import org.eclipse.acceleo.query.runtime.impl.EvaluationServices;
import org.eclipse.acceleo.query.runtime.impl.Nothing;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryValidationEngine;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.acceleo.aql.business.AQLSiriusPlugin;
import org.eclipse.sirius.common.acceleo.aql.business.api.AQLConstants;
import org.eclipse.sirius.common.acceleo.aql.business.api.ExpressionTrimmer;
import org.eclipse.sirius.common.acceleo.aql.business.api.TypesUtil;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.InterpreterStatusFactory;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.osgi.framework.Bundle;

/**
 * A Sirius interpreter using the Acceleo Query Language. It only supports
 * expressions which are not using implicit variables.
 *
 * @author cedric
 */
public class AQLSiriusInterpreter extends AcceleoAbstractInterpreter {

    private final Collection<String> imports = new LinkedHashSet<String>();

    private LoadingCache<String, AstResult> parsedExpressions;

    private IQueryEnvironment queryEnvironment;

    private ECrossReferenceAdapter siriusXref;

    private CrossReferenceProvider xRef = new CrossReferenceProvider() {

        @Override
        public Collection<Setting> getInverseReferences(EObject self) {
            if (siriusXref != null) {
                return siriusXref.getInverseReferences(self);

            } else {
                return Collections.EMPTY_SET;
            }
        }
    };

    /**
     * Create a new interpreter supporting the AQL evaluation engine.
     */
    public AQLSiriusInterpreter() {
        this.queryEnvironment = new QueryEnvironment(xRef);
        final IQueryBuilderEngine builder = new QueryBuilderEngine(queryEnvironment);
        this.parsedExpressions = CacheBuilder.newBuilder().maximumSize(500).build(new CacheLoader<String, AstResult>() {

            @Override
            public AstResult load(String key) throws Exception {
                return builder.build(key);
            }

        });
        this.queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        this.queryEnvironment.registerCustomClassMapping(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class);
        registerEcoreModels(EPackage.Registry.INSTANCE);

    }

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        Set<EPackage> additionalEPackages = Sets.newLinkedHashSet();
        for (MetamodelDescriptor descriptor : metamodels) {
            if (descriptor instanceof EcoreMetamodelDescriptor) {
                EPackage pkg = ((EcoreMetamodelDescriptor) descriptor).resolve();
                if (pkg != null) {
                    additionalEPackages.add(pkg);
                }
            }
        }
        for (EPackage ePackage : additionalEPackages) {
            this.queryEnvironment.registerEPackage(ePackage);
        }
    }

    @Override
    public void addImport(String dependency) {
        if (dependency != null && dependency.contains(".") && !imports.contains(dependency)) {
            imports.add(dependency);
        }
        updateServiceClasses();
    }

    private void updateServiceClasses() {
        List<Class> classesToLoad = Lists.newArrayList();
        for (String qualifiedName : this.imports) {
            Class found = null;
            Iterator<String> it = viewpointPlugins.iterator();
            while (found == null && it.hasNext()) {
                String bundleID = it.next();
                found = loadClassInBundle(bundleID, qualifiedName);
            }
            if (found != null) {
                classesToLoad.add(found);

            }

        }
        for (Class found : classesToLoad) {
            try {
                queryEnvironment.registerServicePackage(found);
            } catch (InvalidAcceleoPackageException e) {
                AQLSiriusPlugin.getPlugin().log(new Status(IStatus.WARNING, AQLSiriusPlugin.getPlugin().getSymbolicName(), e.getMessage(), e));
            }
        }

    }

    private Class loadClassInBundle(String bundleID, String qualifiedName) {
        Bundle requiredBundle = Platform.getBundle(bundleID);
        if (requiredBundle != null) {
            return loadClassInBundle(requiredBundle, qualifiedName);
        }
        return null;

    }

    private Class loadClassInBundle(Bundle bundle, String qualifiedName) {
        try {
            return bundle.loadClass(qualifiedName);
        } catch (ClassNotFoundException e) {
            /*
             * nothing to report, move along to the next bundle.
             */
        } catch (NoClassDefFoundError e) {
            /*
             * nothing to report, move along to the next bundle.
             */
        }
        return null;
    }

    @Override
    public void clearImports() {
        this.imports.clear();
        updateServiceClasses();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public Object evaluate(EObject target, String fullExpression) throws EvaluationException {
        String expression = new ExpressionTrimmer(fullExpression).getExpression();
        Map<String, Object> variables = Maps.newLinkedHashMap(getVariables());
        variables.put("self", target);
        if (target != null && target.eResource() != null && target.eResource().getResourceSet() != null) {
            registerEcoreModels(target.eResource().getResourceSet().getPackageRegistry());
        }
        AstResult build;
        try {
            build = parsedExpressions.get(expression);
            Expression ast = build.getAst();
            AstEvaluator evaluator = new AstEvaluator(new EvaluationServices(queryEnvironment, false));
            Object result = evaluator.eval(variables, ast);
            if (result instanceof Nothing) {
                result = null;
            }
            return result;
        } catch (ExecutionException e) {
            throw new EvaluationException(e.getCause());
        }
    }

    private void registerEcoreModels(Registry packageRegistry) {
        for (String nsURI : packageRegistry.keySet()) {
            EPackage pak = packageRegistry.getEPackage(nsURI);
            if (pak != null && this.queryEnvironment.getEPackageProvider().getEPackage(pak.getNsPrefix()) == null) {
                this.queryEnvironment.registerEPackage(pak);
            }
        }
    }

    @Override
    public boolean evaluateBoolean(EObject context, String expression) throws EvaluationException {
        Object result = evaluate(context, expression);
        final Boolean coerced;
        if (result instanceof Boolean) {
            coerced = (Boolean) result;
        } else {
            coerced = coerceValue(result, Boolean.class);
        }

        if (coerced != null) {
            return coerced.booleanValue();
        }
        return false;
    }

    @Override
    public Collection<EObject> evaluateCollection(EObject context, String expression) throws EvaluationException {
        Object result = evaluate(context, expression);
        Collection<EObject> coercedResult = Lists.newArrayList();
        if (result instanceof Collection<?>) {
            Iterables.addAll(coercedResult, Iterables.filter((Collection<?>) result, EObject.class));
        } else if (result != null && result.getClass().isArray()) {
            Iterables.addAll(coercedResult, Iterables.filter(Lists.newArrayList((Object[]) result), EObject.class));
        } else {
            EObject coerced = coerceValue(result, EObject.class);
            if (coerced != null) {
                coercedResult.add(coerced);
            }
        }

        return coercedResult;
    }

    @Override
    public EObject evaluateEObject(EObject context, String expression) throws EvaluationException {
        Object result = evaluate(context, expression);
        EObject coerced = coerceValue(result, EObject.class);
        return coerced;
    }

    @Override
    public Integer evaluateInteger(EObject context, String expression) throws EvaluationException {
        Object result = evaluate(context, expression);
        Integer coerced = coerceValue(result, Integer.class);
        return coerced;
    }

    @Override
    public String evaluateString(EObject context, String expression) throws EvaluationException {
        Object result = evaluate(context, expression);
        if (result != null) {
            return result.toString();
        }
        return null;
    }

    @Override
    public Collection<String> getImports() {
        return Collections.<String> unmodifiableCollection(this.imports);
    }

    @Override
    public String getVariablePrefix() {
        /*
         * no variable prefix for this interpreter.
         */
        return null;
    }

    @Override
    public void removeImport(String dependency) {
        if (this.imports.contains(dependency)) {
            this.imports.remove(dependency);
        }
        updateServiceClasses();
    }

    @Override
    public void setCrossReferencer(ECrossReferenceAdapter crossReferencer) {
        this.siriusXref = crossReferencer;
    }

    @Override
    public void setModelAccessor(ModelAccessor modelAccessor) {
        /*
         * nothing to do
         */
    }

    @Override
    public void setProperty(Object key, Object value) {
        super.setProperty(key, value);
        if (IInterpreter.FILES.equals(key)) {
            /*
             * this.viewpointPlugins and/or this.viewpointProjects might have
             * been updated. We have to update the loaded classes.
             */
            updateServiceClasses();
        }
    }

    @Override
    public boolean supportsValidation() {
        return true;
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String fullExpression) {
        String trimmedExpression = new ExpressionTrimmer(fullExpression).getExpression();

        for (EPackage pak : context.getAvailableEPackages()) {
            if (pak != null) {
                queryEnvironment.registerEPackage(pak);
            }
        }
        Map<String, Set<IType>> variableTypes = TypesUtil.createAQLVariableTypesFromInterpreterContext(context, queryEnvironment);

        List<IInterpreterStatus> statuses = Lists.newArrayList();
        QueryValidationEngine validator = new QueryValidationEngine(this.queryEnvironment);
        try {
            IValidationResult validationResult = validator.validate(trimmedExpression, variableTypes);
            for (IValidationMessage message : validationResult.getMessages()) {
                IInterpreterStatus status = InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.WARNING, message.getMessage());
                statuses.add(status);
            }
        } catch (AcceleoQueryValidationException e) {
            statuses.add(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.ERROR, e.getMessage()));
            AQLSiriusPlugin.getPlugin().log(new Status(IStatus.ERROR, AQLSiriusPlugin.getPlugin().getSymbolicName(), e.getMessage(), e));
        } catch (AcceleoQueryEvaluationException e) {
            statuses.add(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.ERROR, e.getMessage()));
        }
        if (statuses.size() == 0) {
            // TODO check the return type of the expression matches or will be
            // adapted to the expected type
        }
        return statuses;
    }

    /**
     * return the cross reference provider used by this interpreter instance.
     * 
     * @return the cross reference provider used by this interpreter instance.
     */
    public CrossReferenceProvider getCrossReferenceProvider() {
        return xRef;
    }

    @Override
    public String getPrefix() {
        return AQLConstants.AQL_PREFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean provides(String expression) {
        return expression != null && expression.startsWith(AQLConstants.AQL_PREFIX);
    }

    /**
     * Tries and coerce the given <em>object</em> to an instance of the given
     * class.
     * 
     * @param <T>
     *            Type to which we need to coerce <em>object</em>.
     * @param object
     *            The object we need to coerce to a given {@link Class}.
     * @param clazz
     *            Class to which we are to cast <em>object</em>.
     * @return <em>object</em> cast to type <em>T</em> if possible,
     *         <code>null</code> if not.
     */
    @SuppressWarnings("unchecked")
    protected static <T> T coerceValue(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }

        T result = null;
        if (clazz.isInstance(object)) {
            result = (T) object;
        } else if (object instanceof IAdaptable) {
            result = (T) ((IAdaptable) object).getAdapter(clazz);
        }

        if (result == null) {
            result = (T) Platform.getAdapterManager().getAdapter(object, clazz);
        }

        return result;
    }

    /**
     * The query environment currently used by this interpreter.
     * 
     * @return The query environment currently used by this interpreter.
     */
    public IQueryEnvironment getQueryEnvironment() {
        return this.queryEnvironment;
    }
}
