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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Collections;
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
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.acceleo.aql.business.AQLSiriusPlugin;
import org.eclipse.sirius.common.acceleo.aql.business.api.AQLConstants;
import org.eclipse.sirius.common.acceleo.aql.business.api.ExpressionTrimmer;
import org.eclipse.sirius.common.acceleo.aql.business.api.TypesUtil;
import org.eclipse.sirius.common.tools.api.interpreter.ClassLoadingCallback;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.InterpreterStatusFactory;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A Sirius interpreter using the Acceleo Query Language. It only supports
 * expressions which are not using implicit variables.
 *
 * @author cedric
 */
public class AQLSiriusInterpreter extends AcceleoAbstractInterpreter {

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

    private final ClassLoadingCallback callback = new ClassLoadingCallback() {

        @Override
        public void loaded(String qualifiedName, Class<?> clazz) {
            try {
                queryEnvironment.registerServicePackage(clazz);
            } catch (InvalidAcceleoPackageException e) {
                AQLSiriusPlugin.INSTANCE.log(new Status(IStatus.WARNING, AQLSiriusPlugin.INSTANCE.getSymbolicName(), "Error loading Java extension class " + qualifiedName + " :" + e.getMessage(), e));
            }

        }

        @Override
        public void notFound(String qualifiedName) {
            AQLSiriusPlugin.INSTANCE.log(new Status(IStatus.WARNING, AQLSiriusPlugin.INSTANCE.getSymbolicName(), "Could not find Java extension class " + qualifiedName));

        }

        @Override
        public void unloaded(String qualifiedName, Class<?> clazz) {
            // TODO implement the un-register once it is available in AQL.
            // see Bug 461072

        }
    };

    /**
     * Create a new interpreter supporting the AQL evaluation engine.
     */
    public AQLSiriusInterpreter() {
        super();
        this.queryEnvironment = new QueryEnvironment(xRef);
        this.javaExtensions.addClassLoadingCallBack(callback);
        final IQueryBuilderEngine builder = new QueryBuilderEngine(queryEnvironment);
        this.parsedExpressions = CacheBuilder.newBuilder().maximumSize(500).build(new CacheLoader<String, AstResult>() {

            @Override
            public AstResult load(String key) throws Exception {
                return builder.build(key);
            }

        });
        this.queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
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
    public void dispose() {
        super.dispose();
        this.javaExtensions.removeClassLoadingCallBack(callback);
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
    public String getVariablePrefix() {
        /*
         * no variable prefix for this interpreter.
         */
        return null;
    }

    @Override
    public void setCrossReferencer(ECrossReferenceAdapter crossReferencer) {
        this.siriusXref = crossReferencer;
    }

    @Override
    public void setModelAccessor(ModelAccessor modelAccessor) {
        /*
         * AQL does not support the ModelAccessor yet.
         */
    }

    @Override
    public boolean supportsValidation() {
        return true;
    }

    @Override
    public ValidationResult analyzeExpression(IInterpreterContext context, String fullExpression) {

        String trimmedExpression = new ExpressionTrimmer(fullExpression).getExpression();
        ValidationResult result = new ValidationResult();

        Map<String, Set<IType>> variableTypes = TypesUtil.createAQLVariableTypesFromInterpreterContext(context, queryEnvironment);

        QueryValidationEngine validator = new QueryValidationEngine(this.queryEnvironment);
        try {
            IValidationResult validationResult = validator.validate(trimmedExpression, variableTypes);
            for (IValidationMessage message : validationResult.getMessages()) {
                result.addStatus(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.WARNING, message.getMessage()));
            }
            List<String> classifierNames = Lists.newArrayList();
            for (IType type : validationResult.getPossibleTypes(validationResult.getAstResult().getAst())) {
                if (type instanceof EClassifierType) {
                    EClassifierType eClassifierType = (EClassifierType) type;
                    if (eClassifierType.getType() != null && eClassifierType.getType().getName() != null) {
                        String typeName = eClassifierType.getType().getName();
                        if (eClassifierType.getType().getEPackage() != null && eClassifierType.getType().getEPackage().getName() != null) {
                            typeName = eClassifierType.getType().getEPackage().getName() + "." + typeName;
                        }
                        classifierNames.add(typeName);
                    }
                }
                result.setReturnType(VariableType.fromStrings(classifierNames));
            }
        } catch (AcceleoQueryValidationException e) {
            result.addStatus(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.ERROR, e.getMessage()));
            AQLSiriusPlugin.INSTANCE.log(new Status(IStatus.ERROR, AQLSiriusPlugin.INSTANCE.getSymbolicName(), e.getMessage(), e));
        } catch (AcceleoQueryEvaluationException e) {
            result.addStatus(InterpreterStatusFactory.createInterpreterStatus(context, IInterpreterStatus.ERROR, e.getMessage()));
        }

        return result;
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
     * The query environment currently used by this interpreter.
     * 
     * @return The query environment currently used by this interpreter.
     */
    public IQueryEnvironment getQueryEnvironment() {
        return this.queryEnvironment;
    }
}
