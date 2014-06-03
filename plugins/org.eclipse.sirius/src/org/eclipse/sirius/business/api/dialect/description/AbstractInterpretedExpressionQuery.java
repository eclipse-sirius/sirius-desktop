/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.description;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Query allowing to get the target domain classes and available packages for a
 * given Interpreted expression.
 * 
 * @since 0.9.0
 * @author alagarde
 * 
 */
public abstract class AbstractInterpretedExpressionQuery implements IInterpretedExpressionQuery {

    /**
     * The source tag used in the meta-model for all EAnnotations concerning the
     * variables available to interpreted expressions.
     */
    protected static final String VARIABLES_ANNOTATION_SOURCE = "http://www.eclipse.org/sirius/interpreted/expression/variables";

    /**
     * The character used in the documentation annotation of variables to
     * separate the optional type name from the actual documentation.
     * 
     * @see AbstractInterpretedExpressionQuery#collectLocalVariablesDefinitions()
     */
    protected static final char TYPE_DEFINTION_SEPARATOR = '|';

    /**
     * The key used in {@link #VARIABLES_ANNOTATION_SOURCE} annotations of
     * variable to specify the type of that variable.
     */
    protected static final String VARIABLE_TYPE_KEY = "type";

    /**
     * The type to use for variables for which nothing more specific could be
     * found.
     */
    protected static final String DEFAULT_VARIABLE_TYPE = "ecore.EObject";

    /**
     * The target containing the InterpretedExpression (NodeMapping,
     * ModelOperation...).
     */
    protected EObject target;

    /**
     * The feature corresponding to the InterpretedExpression to evaluate (
     * NodeMapping.semanticCandidatesExpression...).
     */
    protected EStructuralFeature feature;

    /**
     * The expected DomainClass(es) for the given expression.
     */
    protected Option<Collection<String>> targetDomainClass;

    /**
     * The list of EPackages to import to be able to interpret the given
     * expression.
     */
    protected Collection<EPackage> packagesToImport;

    /**
     * The list of dependencies to import to be able to interpret the given
     * expression.
     */
    protected Collection<String> dependencies;

    /**
     * A map representing all available variables. Keys of the map are the
     * variable names, and values of the map their type.
     */
    protected Map<String, String> availableVariables;

    /**
     * The available {@link IInterpretedExpressionTargetSwitch} that will be
     * used to calculate target types.
     */
    protected IInterpretedExpressionTargetSwitch targetSwitch;

    /**
     * Default constructor.
     * 
     * @param target
     *            the target containing the InterpretedExpression (NodeMapping,
     *            ModelOperation...)
     * @param feature
     *            the feature corresponding to the InterpretedExpression to
     *            evaluate ( NodeMapping.semanticCandidatesExpression...), can
     *            be null.
     */
    public AbstractInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        this.target = target;
        this.feature = feature;
        initializeTargetSwitch();
    }

    /**
     * Initializes the switch that will be used to calculate the target Types.
     */
    protected abstract void initializeTargetSwitch();

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery#getTargetDomainClasses()
     */
    public Option<Collection<String>> getTargetDomainClasses() {
        if (targetDomainClass == null) {
            // Use the available TargetSwitches to get the domain class
            targetDomainClass = targetSwitch.doSwitch(target, feature != null);
        }
        return targetDomainClass;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery#getPackagesToImport()
     */
    public Collection<EPackage> getPackagesToImport() {
        if (packagesToImport == null) {
            packagesToImport = Sets.newLinkedHashSet();

            EObject representation = target.eContainer();
            // We get the RepresentationDescription of the target
            while (representation != null && !(representation instanceof RepresentationDescription)) {
                representation = representation.eContainer();
            }
            if (representation != null) {
                // If no EPackage has been explicitly imported
                if (((RepresentationDescription) representation).getMetamodel().isEmpty()) {
                    // We add all available packages
                    for (String nsURI : Sets.newLinkedHashSet(EPackage.Registry.INSTANCE.keySet())) {
                        try {
                            packagesToImport.add(EPackage.Registry.INSTANCE.getEPackage(nsURI));
                            // CHECKSTYLE:OFF
                        } catch (Throwable e) {
                            /*
                             * anything might happen here depending on the other
                             * Eclipse tools, and we've seen many time tools
                             * breaking all the others.
                             */
                            // CHECKSTYLE:ON
                        }
                    }
                } else {
                    // Otherwise, we add the imported packages
                    packagesToImport.addAll(((RepresentationDescription) representation).getMetamodel());
                }
            }
            packagesToImport.add(EcorePackage.eINSTANCE);
        }
        return packagesToImport;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery#getDependencies()
     */
    public Collection<String> getDependencies() {
        if (dependencies == null) {
            dependencies = Sets.newLinkedHashSet();

            if (target != null) {
                EObject vp = target;
                // We get the corresponding viewpoint
                while (vp != null && !(vp instanceof Viewpoint)) {
                    vp = vp.eContainer();
                }
                if (vp != null) {
                    for (JavaExtension dep : ((Viewpoint) vp).getOwnedJavaExtensions()) {
                        if (!StringUtil.isEmpty(dep.getQualifiedClassName())) {
                            dependencies.add(dep.getQualifiedClassName());
                        }
                    }
                }
            }
        }
        return dependencies;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery#getAvailableVariables()
     */
    public Map<String, String> getAvailableVariables() {
        if (availableVariables == null) {
            availableVariables = Maps.newLinkedHashMap();
        }
        Option<EObject> toolContext = getToolContext();
        if (toolContext.some()) {
            collectContextualVariableDefinitions(availableVariables, toolContext.get(), target);
        }
        collectLocalVariablesDefinitions();
        return availableVariables;
    }

    protected Option<EObject> getToolContext() {
        return new EObjectQuery(target).getFirstAncestorOfType(ToolPackage.eINSTANCE.getAbstractToolDescription());
    }

    /**
     * Add all the variables defined locally to the expression, as specified in
     * the meta-model annotations. The type of each variable is encoded in the
     * documentation string associated to each variable.
     * <p>
     * For example, on <code>BasicLabelStyleDescription.labelExpression</code>,
     * an annotation using {@link #VARIABLES_ANNOTATION_SOURCE} has the
     * following entry:
     * <p>
     * <code>
     * diagram -> diagram.DDiagram | the current DSemanticDiagram.
     * </code>
     * <p>
     * where <code>"diagram"</code> is the annotation key, representing the
     * variable's name, and
     * <code>"diagram.DDiagram | the current DSemanticDiagram"</code> is the
     * value, indicating that the type of the variable is
     * <code>diagram.DDiagram</code>.
     * <p>
     * If no type is specified in the documentation,
     * {@link #DEFAULT_VARIABLE_TYPE} is assumed.
     * 
     * @see #TYPE_DEFINTION_SEPARATOR
     */
    private void collectLocalVariablesDefinitions() {
        EAnnotation varAnnotations = feature.getEAnnotation(AbstractInterpretedExpressionQuery.VARIABLES_ANNOTATION_SOURCE);
        if (varAnnotations != null) {
            for (String varName : varAnnotations.getDetails().keySet()) {
                String doc = varAnnotations.getDetails().get(varName);
                String typeName = AbstractInterpretedExpressionQuery.DEFAULT_VARIABLE_TYPE;
                if (doc != null && doc.indexOf(AbstractInterpretedExpressionQuery.TYPE_DEFINTION_SEPARATOR) != -1) {
                    typeName = doc.substring(0, doc.indexOf(AbstractInterpretedExpressionQuery.TYPE_DEFINTION_SEPARATOR)).trim();
                }
                availableVariables.put(varName, typeName);
            }
        }
    }

    /**
     * Collect and merge all the variables definition in the scope between
     * <code>top</code> and <code>bottom</code> (<code>top</code> <em>must</em>
     * be an ancestor of <code>bottom</code>) and merges them. In case of name
     * shadowing, priority is given to the the value defined closest to
     * <code>bottom</code> (lexical scoping).
     */
    private void collectContextualVariableDefinitions(Map<String, String> vars, EObject top, EObject bottom) {
        // A map with multiple values is not strictly required as we only use
        // one value, but it is useful when debugging to have all the information.
        Map<String, Collection<String>> definitions = Maps.newHashMap();
        // Walk up from bottom to top and gather every definition in the scope.
        EObject context = bottom;
        do {
            appendAllLocalVariableDefinitions(definitions, context);
            context = context.eContainer();
        } while (context != null && context != top.eContainer());
        // Merge all the definitions, by taking the one closest to
        // <code>bottom</code> when there are multiple ones.
        for (String var : definitions.keySet()) {
            vars.put(var, ((List<String>) definitions.get(var)).get(0));
        }
    }

    /**
     * Returns the name of the type of the given variable, as defined in the
     * meta-model annotations.
     * 
     * @param var
     *            the variable definition inside a VSM.
     * @return the name of the type for this variables, or
     *         {@link #DEFAULT_VARIABLE_TYPE} if nothing more specified was
     *         specified in the meta-model.
     */
    protected String getVariableTypeName(AbstractVariable var) {
        Preconditions.checkNotNull(var);
        Preconditions.checkNotNull(var.eContainingFeature());

        String typeName = AbstractInterpretedExpressionQuery.DEFAULT_VARIABLE_TYPE;
        EAnnotation varAnnotation = var.eContainingFeature().getEAnnotation(AbstractInterpretedExpressionQuery.VARIABLES_ANNOTATION_SOURCE);
        if (varAnnotation != null && varAnnotation.getDetails().containsKey(AbstractInterpretedExpressionQuery.VARIABLE_TYPE_KEY)) {
            typeName = varAnnotation.getDetails().get(AbstractInterpretedExpressionQuery.VARIABLE_TYPE_KEY);
        }
        return typeName;
    }

    /**
     * Append all the variable definitions defined locally by
     * <code>context</code> to the specified map. Sub-classes should override
     * this method (and call <code>super()</code>) to add any variable
     * definitions specific to their languages/tasks/tools.
     * 
     * @param definitions
     *            the map in which to append all the variable definitions (
     *            <code>name -> type</code>).
     * @param context
     *            the element which may define new variables.
     */
    protected void appendAllLocalVariableDefinitions(Map<String, Collection<String>> definitions, EObject context) {
        // Tool definitions can contain variables, but they do not share a
        // common type/feature name for this containment, so we must do a
        // eAllContent(). This is ugly, and possibly broken if AbstractVariables
        // exist below the point where the expression we are working on is
        // defined.
        if (context instanceof AbstractToolDescription) {
            for (AbstractVariable var : Iterables.filter(AllContents.of(context, false), AbstractVariable.class)) {
                addDefinition(definitions, var.getName(), getVariableTypeName(var));
            }
        }
        // Some variables may contain sub-variables defined by the user: recurse
        // on them.
        if (context instanceof VariableContainer) {
            for (AbstractVariable subVar : ((VariableContainer) context).getSubVariables()) {
                appendAllLocalVariableDefinitions(definitions, subVar);
            }
        }
        // Base case for normal variable definitions.
        if (context instanceof AbstractVariable) {
            AbstractVariable var = (AbstractVariable) context;
            addDefinition(definitions, var.getName(), getVariableTypeName(var));
        }
        // The CreateInstance model operation implicitly defines a variable to
        // reference the newly created instance.
        if (context instanceof CreateInstance) {
            CreateInstance ci = (CreateInstance) context;
            addDefinition(definitions, ci.getVariableName(), ci.getTypeName());
        }
        // The CreateInstance model operation implicitly defines a variable to
        // reference the newly created instance.
        if (context instanceof For) {
            For f = (For) context;
            addDefinition(definitions, f.getIteratorName(), AbstractInterpretedExpressionQuery.DEFAULT_VARIABLE_TYPE);
        }
    }

    /**
     * Appends to the specified definition list all the implicit variables which
     * will/may be defined at runtime according to the specified edit mask. For
     * example, an exit mask of <code>{0}:{1}</code> implies the existence of
     * two String variables at execution time, <code>arg0</code> and
     * <code>arg1</code>.
     * 
     * @param mask
     *            the edit mask which defines the format string.
     * @param definitions
     *            the set of variables definition to which to append.
     */
    protected void appendEditMaskVariables(EditMaskVariables mask, Map<String, Collection<String>> definitions) {
        Pattern p = Pattern.compile("\\{\\d\\}");
        Matcher m = p.matcher(mask.getMask());
        while (m.find()) {
            String group = m.group();
            String index = group.substring(1, group.length() - 1);
            // Old Acceleo 2-style variables.
            addDefinition(definitions, index, "String");
            // New variable names which should be legal in all query
            // languages, including Acceleo 3.
            addDefinition(definitions, "arg" + index, "String");
        }
    }

    /**
     * Add a new definition for a variable, shadowing any previously added ones.
     * 
     * @param definitions
     *            the definitions of all the variables.
     * @param name
     *            the name of the variable to (re-)define.
     * @param value
     *            the value of the variable to (re-)define.
     */
    protected void addDefinition(Map<String, Collection<String>> definitions, String name, String value) {
        Collection<String> defs = definitions.get(name);
        if (defs == null) {
            defs = Lists.newArrayList();
            definitions.put(name, defs);
        }
        defs.add(value);
    }
}
