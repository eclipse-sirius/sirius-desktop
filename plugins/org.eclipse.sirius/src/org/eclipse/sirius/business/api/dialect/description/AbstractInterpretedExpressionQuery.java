/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES.
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

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
    protected static final String VARIABLES_ANNOTATION_SOURCE = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$

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
    protected static final String VARIABLE_TYPE_KEY = "type"; //$NON-NLS-1$

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
    protected Map<String, VariableType> availableVariables;

    /**
     * The most specific type we could find for the current Receiver.
     */
    protected VariableType selfType;

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
    @Override
    public Option<Collection<String>> getTargetDomainClasses() {
        if (targetDomainClass == null) {
            /*
             * the "self" variable might be redefined by a containing
             * ModelOperation (CreateInstance, ChangeContext by example). We
             * have to trigger the computation of the available variables as
             * this will update the "self" type computation at the same time.
             */
            getAvailableVariables();

            if (selfType != null && selfType.hasDefinition()) {
                Collection<String> possibleTypes = Sets.newLinkedHashSet();
                for (TypeName typeName : selfType.getPossibleTypes()) {
                    possibleTypes.add(typeName.getCompleteName());
                }
                targetDomainClass = Options.fromNullable(possibleTypes);
            } else {
                // Use the available TargetSwitches to get the domain class
                targetDomainClass = targetSwitch.doSwitch(target, feature != null);
            }
        }
        return targetDomainClass;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery#getPackagesToImport()
     */
    @Override
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
            packagesToImport.add(ViewpointPackage.eINSTANCE);
            packagesToImport.add(DescriptionPackage.eINSTANCE);
            packagesToImport.add(ToolPackage.eINSTANCE);
            packagesToImport.add(ValidationPackage.eINSTANCE);
        }
        return packagesToImport;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery#getDependencies()
     */
    @Override
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
    @Override
    public Map<String, VariableType> getAvailableVariables() {
        if (availableVariables == null) {
            availableVariables = Maps.newLinkedHashMap();
        }
        Option<EObject> toolContext = getToolContext();
        if (toolContext.some()) {
            EObject operationContext = toolContext.get();
            collectContextualVariableDefinitions(operationContext, target);
            if (operationContext instanceof ToolDescription) {
                /*
                 * the containerView variable is accessible in any Model
                 * operation which is a child of the ToolDescription.
                 */
                availableVariables.put("containerView", VariableType.fromString("viewpoint.DSemanticDecorator")); //$NON-NLS-1$ //$NON-NLS-2$
            }
            addVariablesFromToolContext(operationContext);
        }
        collectLocalVariablesDefinitions();
        if (this.target instanceof ToolDescription && feature == ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION) {
            /*
             * the containerView variable is accessible in the "precondition"
             * feature of the ToolDescription. See GenericToolCommandBuilder.
             */
            availableVariables.put("containerView", VariableType.fromString("viewpoint.DSemanticDecorator")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT.equals(feature)) {
            collectContextualVariableDefinitions(target, target);
            addVariablesFromToolContext(target);
            addVariablesFromCreateOperation(target);
        }

        return availableVariables;
    }

    /**
     * Add variables specific to each dialect and each tool.
     * 
     * @param toolContext
     *            the tool from which to get the variables
     */
    protected void addVariablesFromToolContext(EObject toolContext) {
    }

    /**
     * Add variables from create operations under the tool.
     * 
     * @param toolContext
     *            the tool from which to get the create operation variables
     */
    private void addVariablesFromCreateOperation(EObject toolContext) {
        TreeIterator<EObject> eAllContents = toolContext.eAllContents();
        while (eAllContents.hasNext()) {
            EObject eObject = eAllContents.next();
            if (eObject instanceof ModelOperation) {
                addVariableFromCreateOperation((ModelOperation) eObject);
            }
        }
    }

    /**
     * Add variables for create operation.
     * 
     * @param modelOperation
     *            the potential create operation from which to get the variable
     */
    protected void addVariableFromCreateOperation(ModelOperation modelOperation) {
        if (modelOperation instanceof CreateInstance) {
            availableVariables.put(((CreateInstance) modelOperation).getVariableName(), VariableType.fromString(((CreateInstance) modelOperation).getTypeName()));
        }
    }

    /**
     * return the EObject which represents the top-level execution context.
     * 
     * @return the EObject which represents the top-level execution context.
     */
    protected Option<EObject> getToolContext() {
        Option<EObject> found = Options.newNone();
        /*
         * ValidationFix can contains operations and is not a subclas of a tool.
         * We need to return it as the "tool context" or the logic will find the
         * global diagram definition instead.
         */
        found = new EObjectQuery(target).getFirstAncestorOfType(org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.eINSTANCE.getValidationRule());
        if (!found.some()) {
            found = new EObjectQuery(target).getFirstAncestorOfType(ToolPackage.eINSTANCE.getAbstractToolDescription());
            if (found.some() && found.get() instanceof ExternalJavaAction) {
                /*
                 * an ExternalJavaAction is a special case as it can also be
                 * embedded as an Operation. We need to make sure it is not the
                 * case.
                 */
                EObject container = found.get().eContainer();
                if (container instanceof ModelOperation || container instanceof InitialOperation) {
                    found = new EObjectQuery(container).getFirstAncestorOfType(ToolPackage.eINSTANCE.getAbstractToolDescription());
                }
            }
        }
        return found;
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
        if (feature != null) {
            EAnnotation varAnnotations = feature.getEAnnotation(AbstractInterpretedExpressionQuery.VARIABLES_ANNOTATION_SOURCE);
            if (varAnnotations != null) {
                for (String varName : varAnnotations.getDetails().keySet()) {
                    String doc = varAnnotations.getDetails().get(varName);
                    VariableType typeName = VariableType.ANY_EOBJECT;
                    if (doc != null && doc.indexOf(AbstractInterpretedExpressionQuery.TYPE_DEFINTION_SEPARATOR) != -1) {
                        typeName = VariableType.fromString(doc.substring(0, doc.indexOf(AbstractInterpretedExpressionQuery.TYPE_DEFINTION_SEPARATOR)).trim());
                    }
                    if (!availableVariables.containsKey(varName)) {
                        availableVariables.put(varName, typeName);
                    }
                }
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
    private void collectContextualVariableDefinitions(EObject top, EObject bottom) {
        // A map with multiple values is not strictly required as we only use
        // one value, but it is useful when debugging to have all the
        // information.
        Map<String, Collection<VariableType>> definitions = Maps.newHashMap();
        // Walk up from bottom to top and gather every definition in the scope.
        EObject context = bottom;

        while (context != null && context != top.eContainer()) {
            appendAllLocalVariableDefinitions(definitions, context);
            /*
             * Any ModelOperation which require to call IInterpreter to get more
             * typing information should not create a new context at the
             * ModelOperation leaf or we'll end up with an inifite loop.
             */
            collectContextualVariableForOperation(context, definitions, bottom);
            if (context != top) {
                EObject sibling = precedingSibling(context);
                while (sibling != null) {
                    appendAllLocalVariableDefinitions(definitions, sibling);
                    sibling = precedingSibling(sibling);
                }
            }
            context = context.eContainer();

        }

        // Merge all the definitions, by taking the one closest to
        // <code>bottom</code> when there are multiple ones.
        for (Entry<String, Collection<VariableType>> var : definitions.entrySet()) {
            availableVariables.put(var.getKey(), var.getValue().iterator().next());
        }
    }

    /**
     * Collect the contextual variable for a given operation.
     * 
     * @param current
     *            the current model operation while going from the leaf to the
     *            top.
     * @param definitions
     *            the current state of variable definitions to be updated when
     *            needed.
     * @param leaf
     *            the leaf operation.
     */
    protected void collectContextualVariableForOperation(EObject current, Map<String, Collection<VariableType>> definitions, EObject leaf) {
        if (current != leaf) {
            if (current instanceof ChangeContext) {
                ChangeContext f = (ChangeContext) current;
                IInterpreterContext iContext = SiriusInterpreterContextFactory.createInterpreterContext(f, ToolPackage.Literals.CHANGE_CONTEXT__BROWSE_EXPRESSION);
                ValidationResult res = MultiLanguagesValidator.getInstance().validateExpression(iContext, f.getBrowseExpression());
                VariableType returnTypes = res.getReturnTypes();
                changeSelfType(returnTypes);

            }
            if (current instanceof For) {
                For f = (For) current;
                IInterpreterContext iContext = SiriusInterpreterContextFactory.createInterpreterContext(f, ToolPackage.Literals.FOR__EXPRESSION);
                ValidationResult res = MultiLanguagesValidator.getInstance().validateExpression(iContext, f.getExpression());
                VariableType returnTypes = res.getReturnTypes();
                changeSelfType(returnTypes);
                addDefinition(definitions, f.getIteratorName(), returnTypes);
            }
        }
        if (current instanceof CreateInstance) {
            CreateInstance f = (CreateInstance) current;
            changeSelfType(VariableType.fromString(f.getTypeName()));
        }
    }

    /**
     * Change the type of the "self" context. This method should be called while
     * the model operation structure is being browsed from the leaf to the top.
     * Only the most specific definition of the type of "self" will be kept,
     * further assignations should not be considered.
     * 
     * @param newSelfType
     *            the new type definition for the current "self" context.
     */
    protected void changeSelfType(VariableType newSelfType) {
        /*
         * We only set the self type once as we are browsing the model from most
         * to less specific. The first assignation will be the most specific,
         * further assignations should not be considered.
         */
        if (selfType == null) {
            selfType = newSelfType;
        }
    }

    private EObject precedingSibling(EObject context) {
        EObject container = context.eContainer();
        EStructuralFeature containingFeature = context.eContainingFeature();
        if (container != null && containingFeature != null) {
            Object val = container.eGet(containingFeature);
            /*
             * if val is not a collection then we have no siblings.
             */
            if (val instanceof EList<?>) {
                EList<?> childs = (EList<?>) val;
                int contextPositionInContainingList = childs.indexOf(context);
                if (contextPositionInContainingList > 0) {
                    /*
                     * we have at least one sibling, we return the closest one
                     * to our position going upward.
                     */
                    Object sibling = childs.get(contextPositionInContainingList - 1);
                    if (sibling instanceof EObject) {
                        return (EObject) sibling;
                    }
                }
            }
        }
        return null;
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
    protected VariableType getVariableTypeName(AbstractVariable var) {
        Preconditions.checkNotNull(var);
        Preconditions.checkNotNull(var.eContainingFeature());

        VariableType typeName = VariableType.ANY_EOBJECT;
        EAnnotation varAnnotation = var.eContainingFeature().getEAnnotation(AbstractInterpretedExpressionQuery.VARIABLES_ANNOTATION_SOURCE);
        if (varAnnotation != null && varAnnotation.getDetails().containsKey(AbstractInterpretedExpressionQuery.VARIABLE_TYPE_KEY)) {
            typeName = VariableType.fromString(varAnnotation.getDetails().get(AbstractInterpretedExpressionQuery.VARIABLE_TYPE_KEY));
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
    protected void appendAllLocalVariableDefinitions(Map<String, Collection<VariableType>> definitions, EObject context) {
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
    protected void appendEditMaskVariables(EditMaskVariables mask, Map<String, Collection<VariableType>> definitions) {
        Pattern p = Pattern.compile("\\{\\d\\}"); //$NON-NLS-1$
        Matcher m = p.matcher(mask.getMask());
        while (m.find()) {
            String group = m.group();
            String index = group.substring(1, group.length() - 1);
            // Old Acceleo 2-style variables.
            addDefinition(definitions, index, "String"); //$NON-NLS-1$
            // New variable names which should be legal in all query
            // languages, including Acceleo 3.
            addDefinition(definitions, "arg" + index, "String"); //$NON-NLS-1$ //$NON-NLS-2$
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
    protected void addDefinition(Map<String, Collection<VariableType>> definitions, String name, String value) {
        addDefinition(definitions, name, VariableType.fromString(value));
    }

    /**
     * Add a new definition for a variable, shadowing any previously added ones.
     * 
     * @param definitions
     *            the definitions of all the variables.
     * @param name
     *            the name of the variable to (re-)define.
     * @param type
     *            the variable type
     */
    protected void addDefinition(Map<String, Collection<VariableType>> definitions, String name, VariableType type) {
        Collection<VariableType> defs = definitions.get(name);
        if (defs == null) {
            defs = Lists.newArrayList();
            definitions.put(name, defs);
        }
        defs.add(type);
    }
}
