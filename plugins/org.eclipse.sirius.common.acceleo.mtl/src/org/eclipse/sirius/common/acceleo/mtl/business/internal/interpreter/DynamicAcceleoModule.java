/*****************************************************************************************
 * Copyright (c) 2010, 2012 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *****************************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.common.internal.utils.AcceleoPackageRegistry;
import org.eclipse.acceleo.common.interpreter.CompilationResult;
import org.eclipse.acceleo.common.interpreter.EvaluationResult;
import org.eclipse.acceleo.engine.AcceleoEngineMessages;
import org.eclipse.acceleo.engine.AcceleoEnginePlugin;
import org.eclipse.acceleo.engine.service.AcceleoEvaluationTask;
import org.eclipse.acceleo.engine.service.EvaluationContext;
import org.eclipse.acceleo.model.mtl.Query;
import org.eclipse.acceleo.parser.interpreter.AcceleoCompilationTask;
import org.eclipse.acceleo.parser.interpreter.CompilationContext;
import org.eclipse.acceleo.parser.interpreter.ModuleDescriptor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.ecore.BooleanLiteralExp;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.ecore.PropertyCallExp;
import org.eclipse.ocl.ecore.VariableExp;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.util.Bag;
import org.eclipse.sirius.common.acceleo.mtl.AcceleoMTLInterpreterPlugin;
import org.eclipse.sirius.common.acceleo.mtl.Messages;
import org.eclipse.sirius.ext.base.cache.LRUCache;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * This class has been mostly copy/pasted from
 * org.eclipse.acceleo.internal.ide.ui.interpreter.AcceleoSourceViewer.
 * <p>
 * The OD interpreter does not allow us to configure a source viewer, we then
 * need to externalize here the code required to build a dummy Acceleo module
 * and compile it.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class DynamicAcceleoModule {
    /**
     * This will hold the system specific line separator ("\n" for unix, "\r\n"
     * for dos, "\r" for mac, ...).
     */
    protected static final String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$

    /** This will be used to get a unique identifier for our queries. */
    private static int currentQueryCount;

    /** If we have an import, this will be added to the expression. */
    private static final String IMPORT_PREFIX = "[import "; //$NON-NLS-1$

    /** If we have an import, this will be added to the expression. */
    private static final String IMPORT_SUFFIX = ']' + LINE_SEPARATOR;

    /** We'll use this as the module's signature. */
    private static final String MODULE_PREFIX = "[module temporaryInterpreterModule"; //$NON-NLS-1$

    private static final String MODULE_SUFFIX = ")]" + LINE_SEPARATOR; //$NON-NLS-1$

    /** First argument of the generated queries signatures. */
    private static final String THIS_EOBJECT = "thisEObject"; //$NON-NLS-1$

    /** The current context in Acceleo. */
    private static final String SELF = "self"; //$NON-NLS-1$

    /** We'll use this as a query signature. */
    private static final String DUMMY_QUERY = "[query public {0}(" + THIS_EOBJECT + " : {1}{2}) : OclAny = {3}/]" + LINE_SEPARATOR; //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The System property key to defined the max cache size used in the LRU
     * caches of DynamicAcceleoModule. If this property is not set, the
     * {@link #DEFAULT_MAX_CACHE_SIZE} is used. This property is not documented
     * and exists only to unlock specific situation without building a new
     * version.
     */
    private static final String MAX_CACHE_SIZE_PROPERTY_KEY = AcceleoMTLInterpreterPlugin.PLUGIN_ID + ".maxCacheSize"; //$NON-NLS-1$

    /**
     * The initial size of the caches (compiledModules and queries).
     */
    private static final int INITIAL_CACHE_SIZE = 16;

    /**
     * The default maximum size of the caches (compiledModules and queries).
     * This can be override by using the System property with key
     * {@link #MAX_CACHE_SIZE_PROPERTY_KEY}.
     */
    private static final int DEFAULT_MAX_CACHE_SIZE = 2000;

    /**
     * If the compilation and/or evaluation of this module requires the presence
     * of EPackages that cannot be retrieved through their NsURI only (for
     * example, EPackages located in the workspace, which code has not been
     * generated yet), they can be registered through this.
     */
    private Set<EPackage> additionalEPackages = Sets.newLinkedHashSet();

    /**
     * Remembers the result of the last compilation. This will be reset to
     * <code>null</code> whenever we need to recompile.
     */
    private CompilationResult compilationResult;

    /**
     * Keeps track of the modules we have already built.<BR>
     * Use a LRU cache for this map to avoid having a too large memory
     * footprint.
     */
    private final Map<ModuleDescriptor, CompilationResult> compiledModules;

    /**
     * This will map the identifier of our query (the couple 'target EObject'
     * <-> 'body') to the actual built queries.<BR>
     * Use a LRU cache for this map to avoid having a too large memory
     * footprint.
     */
    private final Map<QueryIdentifier, String> queries;

    /** Use a single resource set for all of our dummies. */
    private ResourceSet resourceSet;

    {
        initializeResourceSet();
    }

    /**
     * Default constructor.
     */
    public DynamicAcceleoModule() {
        // Use the default max cache size except if one is defined in the
        // environment with property MAX_CACHE_SIZE_PROPERTY_KEY.
        int maxCacheSize = DEFAULT_MAX_CACHE_SIZE;
        try {
            String propertyCacheSize = System.getProperty(MAX_CACHE_SIZE_PROPERTY_KEY);
            try {
                maxCacheSize = Integer.valueOf(propertyCacheSize).intValue();
            } catch (NumberFormatException e) {
                // Do nothing just keep the default cache size.
            }
        } catch (SecurityException e) {
            // Do nothing just keep the default cache size.
        }
        // Define a LRU Cache for queries
        queries = new LRUCache<QueryIdentifier, String>(INITIAL_CACHE_SIZE, maxCacheSize);

        // Define a LRU Cache for compiled modules that also removes the
        // corresponding resource from the resource set.
        compiledModules = new LRUCache<ModuleDescriptor, CompilationResult>(INITIAL_CACHE_SIZE, maxCacheSize) {
            /**
             * The serial version id.
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(final Map.Entry<ModuleDescriptor, CompilationResult> eldest) {
                boolean shouldEvict = super.removeEldestEntry(eldest);

                if (shouldEvict) {
                    Object compiledExpression = eldest.getValue().getCompiledExpression();
                    if (compiledExpression instanceof EObject) {
                        EObject compiledExpressionEObject = (EObject) compiledExpression;
                        Resource resource = compiledExpressionEObject.eResource();
                        if (resource != null) {
                            resourceSet.getResources().remove(resource);
                        }
                    }
                }

                return shouldEvict;
            }
        };

    }

    /**
     * Builds all [import ...] lines corresponding to the given list of imports.
     * 
     * @param imports
     *            The imports for which to build Acceleo 3 declarations.
     * @return The whole list of import declarations.
     */
    private static String buildImportDeclarations(Iterable<String> imports) {
        final StringBuilder importsBuffer = new StringBuilder();

        for (String dependency : imports) {
            importsBuffer.append(IMPORT_PREFIX);
            importsBuffer.append(dependency);
            importsBuffer.append(IMPORT_SUFFIX);
        }

        return importsBuffer.toString();
    }

    /**
     * Builds the signature of this module. This will make use of the target
     * EObject(s) and all available variables to determine the NsURIs of the
     * target metamodels.
     * 
     * @param context
     *            The current compilation context.
     * @param queryName
     *            Name of the query for which we're creating this module.
     * @return The useable module signature for the given context.
     */
    private String buildModuleSignature(CompilationContext context, String queryName) {
        return MODULE_PREFIX + queryName + '(' + buildNsURIString(context) + MODULE_SUFFIX;
    }

    /**
     * Builds the String that is to be used for the NsURIs accessible to the
     * module(s) to compile.
     * 
     * @param context
     *            The current compilation context.
     * @return A String useable in a module signature composed of all accessible
     *         nsURIs.
     */
    private String buildNsURIString(CompilationContext context) {
        final Set<String> metamodelURIs = Sets.newLinkedHashSet(context.getNsURIs());

        for (EPackage additionalEPackage : additionalEPackages) {
            metamodelURIs.add(additionalEPackage.getNsURI());
        }
        if (metamodelURIs.isEmpty()) {
            // Use ecore as the default metamodel
            metamodelURIs.add(EcorePackage.eNS_URI);
        }

        StringBuilder nsURIs = new StringBuilder();
        Iterator<String> uriIterator = metamodelURIs.iterator();
        while (uriIterator.hasNext()) {
            nsURIs.append("\'" + uriIterator.next() + "\'"); //$NON-NLS-1$//$NON-NLS-2$
            if (uriIterator.hasNext()) {
                nsURIs.append(","); //$NON-NLS-1$
            }
        }

        return nsURIs.toString();
    }

    /**
     * This will be used in order to compute the variable bit of our
     * {@link #DUMMY_QUERY} signature.
     * <p>
     * Note that the returned String will start with a comma if there is at
     * least one additional variable to consider.
     * </p>
     * 
     * @param additionalVariables
     *            The map of additional variables for which to build a
     *            signature. May be <code>null</code> or empty.
     * @return An empty list if there were no additional variables, each of
     *         their signatures otherwise.
     */
    private static CharSequence computeVariableSignature(Map<String, String> additionalVariables) {
        if (additionalVariables == null || additionalVariables.isEmpty()) {
            return ""; //$NON-NLS-1$
        }

        final StringBuilder signature = new StringBuilder();

        for (Map.Entry<String, String> var : additionalVariables.entrySet()) {
            final String varName = var.getKey();
            final String varType = var.getValue();

            if (varName != null && varName.length() > 0 && varType != null && varType.length() > 0) {
                signature.append(',');
                signature.append(varName);
                signature.append(':');
                signature.append(varType);
            }
        }

        return signature;
    }

    /**
     * This will trim out of the given <em>expression</em> the leading '[' and
     * trailing '/]' that are set around Acceleo expressions.
     * 
     * @param expression
     *            Expression of which we need the actual body.
     * @return The body of the given Acceleo expression.
     */
    private static String extractBody(String expression) {
        String body = expression;
        if (body.startsWith(IAcceleoConstants.DEFAULT_BEGIN)) {
            body = body.substring(IAcceleoConstants.DEFAULT_BEGIN.length());
        }
        if (body.charAt(body.length() - 1) == ']') {
            final String endMarker = IAcceleoConstants.DEFAULT_END_BODY_CHAR + IAcceleoConstants.DEFAULT_END;
            body = body.substring(0, body.length() - endMarker.length());
        }
        return body;
    }

    /**
     * Extract the name of a module from the given String representation.
     * 
     * @param moduleString
     *            The String representation of an Acceleo module.
     * @return The name of the module represented by the given String.
     *         <code>null</code> if none.
     */
    private static String extractModuleName(String moduleString) {
        final int spaceIndex = moduleString.indexOf(' ');
        final int parenIndex = moduleString.indexOf('(', spaceIndex);
        return moduleString.substring(spaceIndex + 1, parenIndex);
    }

    /**
     * Builds an Acceleo 3 module containing all information that can be
     * retrieved from the given context.
     * 
     * @param context
     *            The current compilation context.
     * @param identifier
     *            Identifier of the query we are to build a module for.
     * @return The fully built Acceleo 3 module ready to be compiled and
     *         executed.
     */
    public String buildMTL(CompilationContext context, QueryIdentifier identifier) {
        final StringBuilder expressionBuffer = new StringBuilder();

        final String queryName = identifier.getQueryName();

        expressionBuffer.append(buildModuleSignature(context, queryName));

        if (context.getDependencies() != null) {
            expressionBuffer.append(buildImportDeclarations(context.getDependencies().keySet()));
        }
        if (context.getExtendedDependencies() != null) {
            final Set<String> oclNamespaceImport = Sets.newLinkedHashSet();
            for (ModuleDescriptor descriptor : context.getExtendedDependencies()) {
                oclNamespaceImport.add(descriptor.getQualifiedName());
            }
            expressionBuffer.append(buildImportDeclarations(oclNamespaceImport));
        }

        expressionBuffer.append(queries.get(identifier));

        return expressionBuffer.toString();
    }

    /**
     * Rebuilds the dummy internal module if necessary, then compiles it. The
     * resulting {@link CompilationResult} holds all information regarding the
     * compilation problems, along with a reference towards the (compiled)
     * {@link org.eclipse.acceleo.model.mtl.Module}.
     * 
     * @param context
     *            The current compilation context.
     * @param queryId
     *            The identifier of the query our {@link CompilationResult}s
     *            should reference. Can be <code>null</code>.
     * @return The result of this compilation, along with the encountered status
     *         if any.
     */
    public CompilationResult compile(CompilationContext context, QueryIdentifier queryId) {
        boolean forceRecompilation = compilationResult == null || compilationResult.getStatus() != null && compilationResult.getStatus().getSeverity() != IStatus.OK;
        final String queryName = queryId.getQueryName();
        if (!forceRecompilation || compilationResult != null && compilationResult.getCompiledExpression() instanceof Query) {
            final Query query = (Query) compilationResult.getCompiledExpression();
            // Make sure that the compilation result is pointing to the accurate
            // query
            if (!queryName.equals(query.getName())) {
                boolean foundMatch = false;
                final Iterator<EObject> siblings = query.eContainer().eContents().iterator();
                while (!foundMatch && siblings.hasNext()) {
                    final EObject sibling = siblings.next();
                    if (sibling instanceof Query && queryName.equals(((Query) sibling).getName())) {
                        compilationResult.setCompiledExpression(sibling);
                        foundMatch = true;
                    }
                }
                if (!foundMatch) {
                    // force recompilation
                    compilationResult = null;
                }
            }
        }

        if (compilationResult == null) {
            final String moduleString = buildMTL(context, queryId);
            final String moduleName = extractModuleName(moduleString);
            // We don't need a "resolvable" URI here
            final URI moduleURI = URI.createURI("http://acceleo.eclipse.org/" //$NON-NLS-1$
                    + moduleName + '.' + IAcceleoConstants.EMTL_FILE_EXTENSION);
            final ModuleDescriptor descriptor = new ModuleDescriptor(moduleURI, moduleName, moduleString);

            final CompilationResult cachedResult = compiledModules.get(descriptor);
            if (cachedResult == null) {
                // Complete extended dependencies if needed, then compile them
                final Set<URI> extendedImports = compileExtendedDependencies(context, resourceSet);
                final Set<URI> importsCopy = Sets.newLinkedHashSet(context.getDependencies().values());

                // Module built in memory, its qualified name is its name alone
                final AcceleoCompilationTask compilationTask = new AcceleoCompilationTask(descriptor, Sets.union(importsCopy, extendedImports), queryName, resourceSet);

                try {
                    compilationResult = compilationTask.call();
                } catch (ExecutionException e) {
                    compilationResult = new CompilationResult(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
                } catch (InterruptedException e) {
                    // Impossible for now, but will be if we ever leverage
                    // threading
                    // capabilities.
                    compilationResult = new CompilationResult(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
                    // CHECKSTYLE:OFF Callable.call throws Exception
                } catch (Exception e) {
                    // CHECKSTYLE:ON
                    compilationResult = new CompilationResult(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
                }
                if (compilationResult != null) {
                    new AcceleoParserTrimUtil().trimEnvironment(compilationResult);
                    compiledModules.put(descriptor, compilationResult);
                }
            } else {
                compilationResult = cachedResult;
            }
        }

        if (compilationResult != null && compilationResult.getStatus() != null && compilationResult.getStatus().getSeverity() == IStatus.ERROR) {
            // Assume that this query is the one that triggered the issue
            removeQuery(context);
            log(compilationResult, context.getExpression());
        }

        return compilationResult;
    }

    /**
     * This will check whether a query already exists for the given context.
     * 
     * @param compilationContext
     *            This should contain all necessary information about the query
     *            : full expression, variables, target type.
     * @return The name of the query corresponding to this expression for this
     *         target type.
     */
    public QueryIdentifier ensureQueryExists(CompilationContext compilationContext) {
        final String queryBody = extractBody(compilationContext.getExpression());
        final Map<String, String> additionalVariables = compilationContext.getVariables();
        QueryIdentifier identifier = new QueryIdentifier(compilationContext.getTargetType(), Maps.newLinkedHashMap(additionalVariables), queryBody);

        String queryName = null;
        final Set<QueryIdentifier> existingIds = queries.keySet();
        // Use hash lookup to determine whether it exists
        if (existingIds.contains(identifier)) {
            // But we have to iterate in order to find this existing key
            /*
             * TODO we might benefit from implementing a Map that exposes a
             * 'getKey'. For now, we're using a LinkedHashMap to benefit from
             * faster iteration.
             */
            Iterator<QueryIdentifier> identifiedIterator = existingIds.iterator();
            while (identifiedIterator.hasNext()) {
                QueryIdentifier existingIdentifier = identifiedIterator.next();
                if (existingIdentifier.equals(identifier)) {
                    identifier = existingIdentifier;
                    queryName = identifier.getQueryName();
                }
            }
        }

        if (queryName == null) {
            queryName = identifier.getQueryName();
            final String targetType = compilationContext.getTargetType();
            final CharSequence additionalVarSignature = computeVariableSignature(additionalVariables);

            final String query = MessageFormat.format(DUMMY_QUERY, queryName, targetType, additionalVarSignature, queryBody);

            queries.put(identifier, query);

            compilationResult = null;
        }

        return identifier;
    }

    /**
     * Evaluates the expression denoted by the given {@link EvaluationContext}.
     * 
     * @param context
     *            {@link EvaluationContext} containing all needed information
     *            for the evaluation.
     * @return The result of this evaluation, along with any problems that may
     *         have occurred.
     */
    public EvaluationResult evaluate(EvaluationContext context) {
        EvaluationResult result = null;

        result = evaluationShortcut(context);

        if (result == null) {
            AcceleoEvaluationTask evaluationTask = new AcceleoEvaluationTask(context);

            try {
                result = evaluationTask.call();
            } catch (ExecutionException e) {
                result = new EvaluationResult(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
            } catch (InterruptedException e) {
                // Impossible for now, but will be if we ever leverage threading
                // capabilities.
                result = new EvaluationResult(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
                // CHECKSTYLE:OFF Callable.call throws Exception
            } catch (Exception e) {
                // CHECKSTYLE:ON
                result = new EvaluationResult(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
            }
        }

        if (result.getStatus().getSeverity() == IStatus.ERROR) {
            AcceleoMTLInterpreterPlugin.getDefault().getLog().log(result.getStatus());
        }

        return result;
    }

    /**
     * We'll shortcut the evaluation in some limited cases.
     * <p>
     * Namely, if the compiled expression is composed of only a single property
     * call potentially preceded by a variable evaluation, or a single variable
     * evaluation.
     * </p>
     * 
     * @param context
     *            The evaluation context.
     * @return The shortcut result if any, <code>null</code> if we cannot
     *         shortcut Acceleo here.
     */
    private EvaluationResult evaluationShortcut(EvaluationContext context) {
        EvaluationResult shortcut = null;

        final Object compiled = context.getCompilationResult().getCompiledExpression();
        if (compiled instanceof Query) {
            final OCLExpression exp = ((Query) compiled).getExpression();
            if (exp instanceof VariableExp) {
                final Object value = getVariableValue(context, exp.getName());
                final IStatus status = createResultStatus(value);
                shortcut = new EvaluationResult(value, status);
            } else if (exp instanceof PropertyCallExp && ((PropertyCallExp) exp).getSource() instanceof VariableExp) {
                final Object source = getVariableValue(context, ((PropertyCallExp) exp).getSource().getName());
                if (source instanceof EObject) {
                    final Object value = ((EObject) source).eGet(((PropertyCallExp) exp).getReferredProperty());
                    final IStatus status = createResultStatus(value);
                    shortcut = new EvaluationResult(value, status);
                }
            } else if (exp instanceof BooleanLiteralExp) {
                final Object value = ((BooleanLiteralExp) exp).getBooleanSymbol();
                final IStatus status = createResultStatus(value);
                shortcut = new EvaluationResult(value, status);
            }
        }

        return shortcut;
    }

    /**
     * Returns the value of the given variable within the given evaluation
     * context.
     * 
     * @param context
     *            Current evaluation context.
     * @param name
     *            Name of the variable for which we need a value.
     * @return The value of the given variable within the given evaluation
     *         context.
     */
    private Object getVariableValue(EvaluationContext context, String name) {
        if (SELF.equals(name) || THIS_EOBJECT.equals(name)) {
            return context.getTargetEObject();
        }

        final List<Object> values = context.getVariables().get(name);

        final Object value;
        if (values.isEmpty()) {
            value = null;
        } else {
            value = values.listIterator(values.size()).previous();
        }
        return value;
    }

    /**
     * Returns the set of all generated query names.
     * 
     * @return The set of all generated query names.
     */
    public Set<String> getGeneratedQueryNames() {
        final Set<QueryIdentifier> identifiers = queries.keySet();
        final Set<String> result = new HashSet<String>(identifiers.size());
        for (QueryIdentifier identifier : identifiers) {
            result.add(identifier.getQueryName());
        }
        return result;
    }

    /**
     * Returns the resource set used by this dynamic module.
     * 
     * @return The resource set used by this dynamic module.
     */
    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    /**
     * Invalidates this dynamic module. All subsequent calls to any of this
     * module's getters will force a full rebuild.
     */
    public void invalidate() {
        queries.clear();
        compiledModules.clear();
        resourceSet.getResources().clear();
        initializeResourceSet();
        compilationResult = null;
    }

    /**
     * This can be used to register additional EPackages for the compilation or
     * evaluation of this module.
     * 
     * @param newPackages
     *            References towards the EPackage that need be registered before
     *            compiling or evaluating this module.
     */
    public void registerAdditionalEPackages(Set<EPackage> newPackages) {
        additionalEPackages = Sets.newLinkedHashSet(newPackages);
        invalidate();
    }

    /**
     * Returns an existing query identifier corresponding to the given context.
     * 
     * @return The query identifier corresponding to the given context if it
     *         exists.
     */
    private QueryIdentifier getQueryIdentifier(CompilationContext context) {
        final String queryBody = extractBody(context.getExpression());
        final Map<String, String> additionalVariables = context.getVariables();
        final QueryIdentifier identifier = new QueryIdentifier(context.getTargetType(), Maps.newLinkedHashMap(additionalVariables), queryBody);

        final Set<QueryIdentifier> existingIds = queries.keySet();
        // Use hash lookup to determine whether it exists
        if (existingIds.contains(identifier)) {
            // But we have to iterate in order to find this existing key
            /*
             * TODO we might benefit from implementing a Map that exposes a
             * 'getKey'. For now, we're using a LinkedHashMap to benefit from
             * faster iteration.
             */
            Iterator<QueryIdentifier> identifiedIterator = existingIds.iterator();
            while (identifiedIterator.hasNext()) {
                QueryIdentifier existingIdentifier = identifiedIterator.next();
                if (existingIdentifier.equals(identifier)) {
                    return existingIdentifier;
                }
            }
        }

        return identifier;
    }

    /**
     * Compiles all extended dependencies, then return the set of their URIs.
     * 
     * @param context
     *            The current compilation context.
     * @param targetResourceSet
     *            The resource set in which to compile all dependencies.
     * @return The set of all of the compiled dependencies URIs.
     */
    public Set<URI> compileExtendedDependencies(CompilationContext context, ResourceSet targetResourceSet) {
        final Set<URI> extendedDependenciesURIs = Sets.newLinkedHashSet();

        for (ModuleDescriptor extended : context.getExtendedDependencies()) {
            ModuleDescriptor actualModule = extended;
            final String moduleContent = extended.getModuleContent();
            final Matcher moduleSignatureMatcher = Pattern.compile("\\[module ([^(]+)\\(([^)]*)\\{0\\}([^)]*)\\)").matcher(moduleContent); //$NON-NLS-1$
            String nsURIString = buildNsURIString(context);
            if (moduleSignatureMatcher.find()) {
                if (moduleSignatureMatcher.group(2).length() > 0) {
                    nsURIString = ',' + nsURIString;
                }
                if (moduleSignatureMatcher.group(3).length() > 0) {
                    nsURIString += ',';
                }
                final String replacement = "[module $1($2" + nsURIString + "$3)"; //$NON-NLS-1$//$NON-NLS-2$
                final String actualModuleContent = moduleSignatureMatcher.replaceFirst(replacement);
                actualModule = new ModuleDescriptor(extended.getModuleURI(), extended.getQualifiedName(), actualModuleContent);
            }

            // TODO When are we removing those modules from their containing
            // resource?
            CompilationResult result = compiledModules.get(actualModule);
            if (result == null || (result.getCompiledExpression() instanceof EObject && ((EObject) result.getCompiledExpression()).eResource() == null)) {
                final AcceleoCompilationTask compilationTask = new AcceleoCompilationTask(actualModule, null, null, targetResourceSet);

                try {
                    result = compilationTask.call();
                } catch (ExecutionException e) {
                    // Ignore this, let the main compilation task fail
                } catch (InterruptedException e) {
                    // Ignore this, let the main compilation task fail
                    // CHECKSTYLE:OFF Callable.call throws Exception
                } catch (Exception e) {
                    // CHECKSTYLE:ON
                    // Ignore this, let the main compilation task fail
                }
                if (result != null) {
                    new AcceleoParserTrimUtil().trimEnvironment(result);
                }
                compiledModules.put(actualModule, result);
            }

            boolean resultOK = result != null && result.getCompiledExpression() instanceof EObject && (result.getStatus() == null || result.getStatus().getSeverity() < IStatus.ERROR);
            if (resultOK) {
                // The module is created with an "http acceleo" URI ...
                // which won't allow us to retrieve the corresponding java
                // file if needed
                assert result != null;
                EObject compiledExpression = (EObject) result.getCompiledExpression();
                extendedDependenciesURIs.add(compiledExpression.eResource().getURI());
            }
        }

        return extendedDependenciesURIs;
    }

    /** (Re)initializes the resource set in which we'll compile the modules. */
    private void initializeResourceSet() {
        resourceSet = new ResourceSetImpl();
        resourceSet.setPackageRegistry(AcceleoPackageRegistry.INSTANCE);
        for (EPackage pack : additionalEPackages) {
            AcceleoPackageRegistry.INSTANCE.registerEcorePackage(pack);
        }
    }

    /**
     * This will be called whenever we encounter a compilation error so as to
     * try and invalidate the error's source.
     * 
     * @param context
     *            The compilation context at the time we got an error.
     */
    private void removeQuery(CompilationContext context) {
        QueryIdentifier queryForContext = getQueryIdentifier(context);
        queries.remove(queryForContext);
    }

    /**
     * This will log a properly formatted compilation error message for the
     * given expression.
     * 
     * @param result
     *            The compilation result which status is to be logged.
     * @param expression
     *            The expression which triggered a compilation error.
     */
    private static void log(CompilationResult result, String expression) {
        final String errorMessage;
        final Throwable cause;
        if (result.getStatus() instanceof MultiStatus && ((MultiStatus) result.getStatus()).getChildren().length > 0) {
            // For now, only consider the first child
            IStatus child = ((MultiStatus) result.getStatus()).getChildren()[0];
            errorMessage = child.getMessage();
            cause = child.getException();
        } else {
            errorMessage = result.getStatus().getMessage();
            cause = result.getStatus().getException();
        }

        final String formattedMessage = MessageFormat.format(Messages.DynamicAcceleoModule_compilationError, expression, errorMessage);
        final IStatus logStatus = new Status(result.getStatus().getSeverity(), result.getStatus().getPlugin(), formattedMessage, cause);
        AcceleoMTLInterpreterPlugin.getDefault().getLog().log(logStatus);
    }

    /**
     * Return the common prefix used in queries of the dynamic module.
     * 
     * @return the common prefix used in queries of the dynamic module.
     */
    public static String getDynamicModuleQueryPrefix() {
        return QueryIdentifier.QUERY_NAME_PREFIX;
    }

    /**
     * This will create the status that allows the interpreter to display the
     * type and size of the resulting object.
     * <p>
     * Copy/pasted from AcceleoEvaluationTask since we cannot open an API in the
     * maintenance version of Acceleo.
     * </p>
     * 
     * @param result
     *            The result of the evaluation.
     * @return Status that allows the interpreter to display the type and size
     *         of the result.
     */
    private IStatus createResultStatus(Object result) {
        final String oclType;
        if (result == null) {
            oclType = "OCLVoid"; //$NON-NLS-1$
        } else {
            oclType = inferOCLType(result);
        }
        String size = null;
        if (result instanceof String) {
            size = String.valueOf(((String) result).length());
        } else if (result instanceof Collection<?>) {
            size = String.valueOf(((Collection<?>) result).size());
        }

        String message = AcceleoEngineMessages.getString("AcceleoEvaluationTask.ResultType", oclType); //$NON-NLS-1$
        if (size != null) {
            message += ' ' + AcceleoEngineMessages.getString("AcceleoEvaluationTask.ResultSize", size); //$NON-NLS-1$
        }

        return new Status(IStatus.OK, AcceleoEnginePlugin.PLUGIN_ID, message);
    }

    /**
     * Tries and infer the OCL type of the given Object.
     * <p>
     * Copy/pasted from AcceleoEvaluationTask since we cannot open an API in the
     * maintenance version of Acceleo.
     * </p>
     * 
     * @param obj
     *            Object for which we need an OCL type.
     * @return The inferred OCL type. OCLAny if we could not infer anything more
     *         sensible.
     */
    private static String inferOCLType(Object obj) {
        String oclType = "OCLAny"; //$NON-NLS-1$
        final EcoreEnvironment env = (EcoreEnvironment) new EcoreEnvironmentFactory().createEnvironment();
        if (obj instanceof Collection<?>) {
            EClassifier elementType = inferCollectionContentOCLType(env, (Collection<?>) obj);
            CollectionKind kind = CollectionKind.SEQUENCE_LITERAL;
            if (obj instanceof LinkedHashSet<?>) {
                kind = CollectionKind.ORDERED_SET_LITERAL;
            } else if (obj instanceof Set<?>) {
                kind = CollectionKind.SET_LITERAL;
            } else if (obj instanceof Bag<?>) {
                kind = CollectionKind.BAG_LITERAL;
            }
            oclType = env.getTypeResolver().resolveCollectionType(kind, elementType).getName();
        } else {
            oclType = getOCLType(env, obj).getName();
        }
        return oclType;
    }

    /**
     * Tries and infer the OCL type of the given Collection's content.
     * <p>
     * Copy/pasted from AcceleoEvaluationTask since we cannot open an API in the
     * maintenance version of Acceleo.
     * </p>
     * 
     * @param env
     *            the ecore environment from which to seek types.
     * @param coll
     *            Collection for which we need an OCL type.
     * @return The inferred OCL type. OCLAny if we could not infer anything more
     *         sensible.
     */
    protected static EClassifier inferCollectionContentOCLType(EcoreEnvironment env, Collection<?> coll) {
        if (coll.isEmpty()) {
            return env.getOCLStandardLibrary().getOclAny();
        }

        Set<EClassifier> types = new HashSet<EClassifier>();
        for (Object child : coll) {
            types.add(getOCLType(env, child));
        }

        Iterator<EClassifier> iterator = types.iterator();

        EClassifier elementType = iterator.next();
        while (iterator.hasNext()) {
            elementType = env.getUMLReflection().getCommonSuperType(elementType, iterator.next());
        }

        if (elementType == null) {
            elementType = env.getOCLStandardLibrary().getOclAny();
        }

        return elementType;
    }

    /**
     * Returns the OCL type of the given Object.
     * <p>
     * Copy/pasted from AcceleoEvaluationTask since we cannot open an API in the
     * maintenance version of Acceleo.
     * </p>
     * 
     * @param env
     *            the ecore environment from which to seek types.
     * @param obj
     *            The Object we need an OCL type for.
     * @return The OCL type of the given Object.
     */
    protected static EClassifier getOCLType(EcoreEnvironment env, Object obj) {
        OCLStandardLibrary<EClassifier> library = env.getOCLStandardLibrary();
        EClassifier oclType = library.getOclAny();
        if (obj instanceof Number) {
            if (obj instanceof BigDecimal || obj instanceof Double || obj instanceof Float) {
                oclType = library.getReal();
            } else {
                oclType = library.getInteger();
            }
        } else if (obj instanceof String) {
            oclType = library.getString();
        } else if (obj instanceof Boolean) {
            oclType = library.getBoolean();
        } else if (obj instanceof EObject) {
            oclType = env.getUMLReflection().asOCLType(((EObject) obj).eClass());
        } else if (obj instanceof Collection<?>) {
            if (obj instanceof LinkedHashSet<?>) {
                oclType = library.getOrderedSet();
            } else if (obj instanceof Set<?>) {
                oclType = library.getSet();
            } else if (obj instanceof Bag<?>) {
                oclType = library.getBag();
            } else {
                oclType = library.getSequence();
            }
        }
        return oclType;
    }

    /**
     * This class will be used by the dynamic module builder to uniquely
     * identify its queries.
     * <p>
     * A query identifier is made up of the couple &lt;target EObject type&gt;
     * &lt;-&get; &lt;expression body&gt;.
     * </p>
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    public final class QueryIdentifier {

        /** This will be used as the prefix for our query names. */
        private static final String QUERY_NAME_PREFIX = "dynamicModuleQuery"; //$NON-NLS-1$

        /** The type's target to assume if none more specific is given. */
        private static final String DEFAULT_TARGET_TYPE = "ecore::EObject"; //$NON-NLS-1$

        /** Keeps a reference to this query's body. */
        private final String body;

        /** The identified query's name. */
        private String queryName;

        /** Keeps a reference to the list of variables accessible by this query. */
        private final Map<String, String> queryVariables;

        /** Keeps a reference to this query's target qualified type. */
        private final String targetType;

        /**
         * Builds an identifier given the target EObject type and the query's
         * body.
         * <p>
         * This will consider all given variables to be in the query's
         * signature.
         * </p>
         * 
         * @param target
         *            The target type of our query.
         * @param queryVariables
         *            The variables accessible to this query.
         * @param expression
         *            The body of our query.
         */
        public QueryIdentifier(String target, Map<String, String> queryVariables, String expression) {
            this.targetType = Objects.firstNonNull(target, QueryIdentifier.DEFAULT_TARGET_TYPE);
            this.queryVariables = queryVariables;
            this.body = expression;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof QueryIdentifier)) {
                return false;
            }

            final QueryIdentifier other = (QueryIdentifier) obj;
            boolean equals = targetType.equals(other.targetType) && body.equals(other.body);
            if (equals) {
                equals = queryVariables == other.queryVariables || (queryVariables != null && queryVariables.equals(other.queryVariables));
            }
            return equals;
        }

        /**
         * Returns the name of the uniquely identified query.
         * 
         * @return The name of the uniquely identified query.
         */
        public String getQueryName() {
            if (queryName == null) {
                queryName = QUERY_NAME_PREFIX + currentQueryCount++;
            }
            return queryName;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            int hashCode = targetType.hashCode() + body.hashCode();
            if (queryVariables != null) {
                hashCode += queryVariables.hashCode();
            }
            return hashCode;
        }
    }
}
