/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.ui.tools.api.dialog.FeatureEditorDialog;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

/**
 * Support for the cells editing of a Edition DTable (with
 * {@link org.eclipse.sirius.table.metamodel.table.DFeatureColumn}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DFeatureColumnEditingSupport extends EditingSupport {

    private final DFeatureColumn featureColumn;

    private final TransactionalEditingDomain editingDomain;

    private final ModelAccessor accessor;

    private final ITableCommandFactory tableCommandFactory;

    private final AbstractDTableEditor tableEditor;

    private final AdapterFactory adapterFactory;

    /**
     * Constructor.
     * 
     * @param viewer
     *            The columnViewer for this editingSupport
     * @param featureColumn
     *            The column
     * @param editingDomain
     *            The transactional editing domain of this viewer
     * @param accessor
     *            The accessor for the model
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableEditor
     *            The associated editor
     */
    public DFeatureColumnEditingSupport(final ColumnViewer viewer, final DFeatureColumn featureColumn, final TransactionalEditingDomain editingDomain, final ModelAccessor accessor,
            final ITableCommandFactory tableCommandFactory, final AbstractDTableEditor tableEditor) {
        super(viewer);
        this.featureColumn = featureColumn;
        this.editingDomain = editingDomain;
        this.accessor = accessor;
        this.tableCommandFactory = tableCommandFactory;
        this.tableEditor = tableEditor;
        final List<ComposedAdapterFactory> factories = new ArrayList<ComposedAdapterFactory>();
        // Add all factories register plug-in registration
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        // Add all dialect factories
        factories.add(new ComposedAdapterFactory(DialectUIManager.INSTANCE.createAdapterFactory()));
        this.adapterFactory = new ComposedAdapterFactory(factories);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(final Object element) {
        boolean result = false;
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            boolean canEdit = true;
            Option<DCell> optCell = TableHelper.getCell(line, featureColumn);
            if (optCell.some()) {
                DCell cell = optCell.get();
                if (cell.getUpdater() != null && cell.getUpdater().getCanEdit() != null && cell.getUpdater().getCanEdit().length() > 0) {
                    final IInterpreter interpreter = InterpreterUtil.getInterpreter(cell.getTarget());
                    try {
                        canEdit = interpreter.evaluateBoolean(cell.getTarget(), cell.getUpdater().getCanEdit());
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(cell.getUpdater(), DescriptionPackage.eINSTANCE.getCellUpdater_CanEdit(), e);
                    }
                }
                result = canEdit && getAuthority().canEditFeature(cell.getTarget(), getFeatureName()) && getAuthority().canEditInstance(line);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (element instanceof DLine) {
            final Option<DCell> editedCell = TableHelper.getCell((DLine) element, featureColumn);
            if (editedCell.some()) {
                final boolean directEdit = editedCell.get().getUpdater() != null && editedCell.get().getUpdater().getDirectEdit() != null;
                return getBestCellEditor(editedCell.get().getTarget(), directEdit);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(final Object element) {
        Object result = null;
        if (element instanceof DLine) {
            try {
                final Option<DCell> optEditedCell = TableHelper.getCell((DLine) element, featureColumn);
                if (!optEditedCell.some()) {
                    return null;
                }
                DCell editedCell = optEditedCell.get();
                final EObject featureParent = editedCell.getTarget();

                boolean directEdit = false;
                if (editedCell.getUpdater() != null && editedCell.getUpdater().getDirectEdit() != null) {
                    directEdit = true;
                }
                if (directEdit) {
                    result = editedCell.getLabel();
                } else {
                    // Read the value in the model and not the value in the
                    // DTable because it can be formatted differently
                    result = getAccessor().eGet(featureParent, getFeatureName());
                }

                if (!isEReference(featureParent) || directEdit) {
                    // If the type of the value is a EEnum and the cellEditor is
                    // not an ExtendedComboBoxCellEditor, we must return the
                    // index of the ComboBox
                    final EClassifier eClassifier = getEClassifier(featureParent);
                    if (eClassifier instanceof EEnum && !(getCellEditor(element) instanceof ExtendedComboBoxCellEditor)) {
                        int index = 0;
                        if (result instanceof String) {
                            index = getIndex((EEnum) eClassifier, (String) result);
                        } else if (result instanceof Enumerator) {
                            index = getIndex((EEnum) eClassifier, ((Enumerator) result).getLiteral());
                        }
                        result = Integer.valueOf(index);
                    } else if (result != null) {
                        if (!(result instanceof Boolean) && !(result instanceof Enumerator)) {
                            result = result.toString();
                        }
                    } else {
                        result = ""; //$NON-NLS-1$
                    }
                }
            } catch (final FeatureNotFoundException e) {
                SiriusPlugin.getDefault().error("Error while getting the property value", e);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    protected void setValue(final Object element, final Object value) {
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            // If the type of the value is a EEnum, we must use the index of the
            // comboBox to get the literal value
            final Option<DCell> optEditedCell = TableHelper.getCell(line, featureColumn);
            if (!optEditedCell.some()) {
                return;
            }
            DCell editedCell = optEditedCell.get();
            final EObject featureParent = editedCell.getTarget();
            final EClassifier eClassifier = getEClassifier(featureParent);
            Object tempValue = value;
            if (eClassifier instanceof EEnum) {
                if (value instanceof Enumerator) {
                    tempValue = ((Enumerator) value).getValue();
                } else if (value != null) {
                    tempValue = ((EEnum) eClassifier).getELiterals().get(((Integer) value).intValue()).getValue();
                }
            } else if (value instanceof String && eClassifier != null) {
                try {
                    if (eClassifier instanceof EDataType) {
                        tempValue = eClassifier.getEPackage().getEFactoryInstance().createFromString((EDataType) eClassifier, (String) value);
                    } else if ("UnlimitedNatural".equals(eClassifier.getName())) { //$NON-NLS-1$
                        tempValue = Integer.valueOf((String) value);
                    }
                } catch (final NumberFormatException e) {
                    tempValue = null;
                }
            }
            try {
                if (tempValue != null || isEReference(featureParent)) {
                    final Object finalValue = tempValue;
                    if (editedCell.getUpdater() != null && editedCell.getUpdater().getDirectEdit() != null) {
                        // Specific set
                        specificSetValue(editedCell, finalValue);
                    } else {
                        // Normal set
                        standardSetValue(line, finalValue);
                    }
                }
            } catch (final ClassCastException e) {
                SiriusPlugin.getDefault().error("Error while setting the property value", e);
            }
        }
    }

    /**
     * Set the value by doing a standard eSet for the feature
     * 
     * @param line
     *            The line containing the feature to set
     * @param value
     *            the new value
     */
    private void standardSetValue(final DLine line, final Object value) {
        final Option<DCell> cell = TableHelper.getCell(line, featureColumn);
        if (cell.some()) {
            getEditingDomain().getCommandStack().execute(new StandardSetValueRecordingCommand(getEditingDomain(), "Set " + getFeatureName() + " value", cell.get(), value));
        }
    }

    /**
     * Set the value by calling the user operations
     * 
     * @param editedCell
     *            The cell to set
     * @param value
     *            the new value
     * 
     */
    private void specificSetValue(final DCell editedCell, final Object value) {
        tableEditor.enablePropertiesUpdate(false);
        Command command = tableCommandFactory.buildSetCellValueFromTool(editedCell, value);
        if (command.canExecute()) {
            getEditingDomain().getCommandStack().execute(command);
        }
        tableEditor.enablePropertiesUpdate(true);
        tableEditor.forceRefreshProperties();
    }

    /**
     * Chooses the best CellEditor depending on the type of value to edit
     * 
     * @param element
     *            The current edited element
     * @param directEdit
     *            true if this cell has a direct edit tool, false otherwise
     * @return An adapted cell Editor
     */
    private CellEditor getBestCellEditor(final EObject element, final boolean directEdit) {
        CellEditor result = null;
        final Tree tree = ((TreeViewer) getViewer()).getTree();
        final EClassifier eClassifier = getEClassifier(element);
        final IItemPropertyDescriptor iItemPropertyDescriptor = getPropertyDescriptor(element);
        if (directEdit) {
            boolean isMultiLine = false;
            if (iItemPropertyDescriptor != null) {
                isMultiLine = iItemPropertyDescriptor.isMultiLine(element);
            }
            result = getBestCellEditorForDirectEdit(tree, isMultiLine);
        } else {
            if (isEReference(element)) {
                final Collection<?> choiceOfValues = iItemPropertyDescriptor.getChoiceOfValues(element);
                if (iItemPropertyDescriptor.isMany(element)) {
                    // CellEditor with button "..." for select the correct
                    // elements
                    boolean valid = true;
                    for (Object choice : choiceOfValues) {
                        if (!eClassifier.isInstance(choice)) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) {
                        result = new ExtendedDialogCellEditor(tree, getLabelProvider(element)) {
                            @Override
                            protected Object openDialogBox(final Control cellEditorWindow) {

                                FeatureEditorDialog dialog = new FeatureEditorDialog(cellEditorWindow.getShell(), getLabelProvider(element), element, eClassifier, (List<?>) doGetValue(),
                                        iItemPropertyDescriptor.getDisplayName(element), new ArrayList<Object>(choiceOfValues), false, iItemPropertyDescriptor.isSortChoices(element), true);
                                dialog.open();
                                return dialog.getResult();
                            }
                        };
                    }
                } else {
                    // We reuse the list of the ItemPropertyDescriptor for
                    // populate the combo
                    result = new ExtendedComboBoxCellEditor(tree, new ArrayList<Object>(choiceOfValues), getLabelProvider(element), iItemPropertyDescriptor.isSortChoices(element));
                }
            } else {
                if (eClassifier != null) {
                    if (eClassifier instanceof EDataType && ("Boolean".equals(((EDataType) eClassifier).getName()) || "EBoolean".equals(((EDataType) eClassifier).getName()))) { //$NON-NLS-1$ //$NON-NLS-2$
                        result = new CheckboxCellEditor(tree);
                    } else if (eClassifier instanceof EEnum) {
                        final Object genericFeature = iItemPropertyDescriptor.getFeature(element);
                        if (genericFeature instanceof EStructuralFeature) {
                            final Collection<?> choiceOfValues = iItemPropertyDescriptor.getChoiceOfValues(element);
                            if (choiceOfValues != null) {
                                result = new ExtendedComboBoxCellEditor(tree, new ArrayList<Object>(choiceOfValues), getLabelProvider(element), iItemPropertyDescriptor.isSortChoices(element));
                            }
                        }
                        if (result == null) {
                            result = new ComboBoxCellEditor(tree, getValues((EEnum) eClassifier).toArray(new String[0]), SWT.READ_ONLY);
                        }
                    } else {
                        int style = SWT.SINGLE;
                        if (iItemPropertyDescriptor != null && iItemPropertyDescriptor.isMultiLine(element)) {
                            style = SWT.WRAP | SWT.MULTI;
                        }
                        final TextCellEditor textEditor = new TextCellEditor(tree, style) {
                            /**
                             * {@inheritDoc} We override the doSetFocus for
                             * clearing the selection for the direct edition of
                             * the cell.
                             * 
                             * @see org.eclipse.jface.viewers.TextCellEditor#doSetFocus()
                             */
                            @Override
                            protected void doSetFocus() {
                                super.doSetFocus();
                                if (text != null) {
                                    text.clearSelection();
                                }
                            }
                        };
                        textEditor.getControl().addFocusListener(new DTableCellEditorFocusListener(tableEditor, textEditor));
                        result = textEditor;
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param tree
     *            The tree
     * @param isMultiLine
     *            true if the text editor must be a multiLine, false otherwise
     * @return the textEditor for a cell with a directEdit tool
     */
    private CellEditor getBestCellEditorForDirectEdit(final Tree tree, final boolean isMultiLine) {
        CellEditor result;
        int style = SWT.SINGLE;
        if (isMultiLine) {
            style = SWT.WRAP | SWT.MULTI;
        }
        final TextCellEditor textEditor = new TextCellEditor(tree, style) {
            /**
             * {@inheritDoc} Override for problem when the feature name defines
             * in the odesign is not valid.
             * 
             * @see org.eclipse.jface.viewers.TextCellEditor#doSetValue(java.lang.Object)
             */
            @Override
            protected void doSetValue(final Object value) {
                if (value != null) {
                    super.doSetValue(value);
                }
            }

            /**
             * {@inheritDoc} We override the doSetFocus for clearing the
             * selection for the direct edition of the cell.
             * 
             * @see org.eclipse.jface.viewers.TextCellEditor#doSetFocus()
             */
            @Override
            protected void doSetFocus() {
                super.doSetFocus();
                if (text != null) {
                    text.clearSelection();
                }
            }
        };
        textEditor.setValidator(new ICellEditorValidator() {
            /**
             * {@inheritDoc} Override for problem when the feature name defines
             * in the odesign is not valid.
             * 
             * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
             */
            public String isValid(final Object value) {
                if (value == null) {
                    return "Null value is not a valid value for this cell.";
                }
                return null;
            }
        });
        textEditor.getControl().addFocusListener(new DTableCellEditorFocusListener(tableEditor, textEditor));
        result = textEditor;
        return result;
    }

    /**
     * Calculate the list of labels for this enumeration
     * 
     * @param enumeration
     *            The enumeration
     * @return A list of labels
     */
    private List<String> getValues(final EEnum enumeration) {
        final EList<EEnumLiteral> listValues = enumeration.getELiterals();
        final List<String> items = new ArrayList<String>(listValues.size());
        for (final EEnumLiteral enumLiteral : listValues) {
            items.add(enumLiteral.getLiteral());
        }
        return items;
    }

    /**
     * Get the classifier of the attribute corresponding the attribute with
     * featureName of the this element.
     * 
     * @param element
     *            The current element
     * @return The eClassifier
     */
    private EClassifier getEClassifier(final EObject element) {
        if (element != null) {
            final EStructuralFeature eStructuralFeature = element.eClass().getEStructuralFeature(getFeatureName());
            if (eStructuralFeature != null) {
                return eStructuralFeature.getEType();
            }
        }
        return null;
    }

    /**
     * The feature name of the column associated with this editingSupport.
     * 
     * @return A the feature name
     */
    protected String getFeatureName() {
        return featureColumn.getFeatureName();
    }

    /**
     * @param enumeration
     *            the enum in which search
     * @param literal
     *            the searched literal
     * @return
     */
    private int getIndex(final EEnum enumeration, final String literal) {
        int result = -1;
        final EList<EEnumLiteral> listValues = enumeration.getELiterals();
        for (int i = 0; i < listValues.size() && result == -1; i++) {
            if (listValues.get(i).getLiteral().equals(literal)) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Return the transactional editing domain.
     * 
     * @return the transactional editing domain
     */
    public TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * @return The permission authority
     */
    private IPermissionAuthority getAuthority() {
        return getAccessor().getPermissionAuthority();
    }

    /**
     * @return the accessor
     */
    private ModelAccessor getAccessor() {
        return accessor;
    }

    /**
     * @param target
     *            The target for which it wishes to check if the feature is a
     *            eReference
     * @return true if the feature name corresponds to a EReference feature,
     *         false otherwise
     */
    private boolean isEReference(final EObject target) {
        if (target == null) {
            return false;
        }
        final EStructuralFeature structuralFeature = target.eClass().getEStructuralFeature(getFeatureName());
        return structuralFeature instanceof EReference;
    }

    /**
     * @param target
     *            The target for which it wishes to check if the feature can
     *            have more than one value
     * @return true if the feature name corresponds to a feature that can hav
     *         more than one value, false otherwise
     */
    private boolean isMany(final EObject target) {
        final EStructuralFeature structuralFeature = target.eClass().getEStructuralFeature(getFeatureName());
        return structuralFeature.isMany();
    }

    /**
     * @param instance
     *            The object for which it wishes to obtain the labelProvider
     * @return A label provider
     */
    private ILabelProvider getLabelProvider(final EObject instance) {
        final IItemPropertyDescriptor itemPropertyDescriptor = getPropertyDescriptor(instance);
        return new LabelProvider() {
            @Override
            public String getText(final Object object) {
                if (itemPropertyDescriptor != null && object != null) {
                    return itemPropertyDescriptor.getLabelProvider(object).getText(object);
                } else {
                    return super.getText(object);
                }
            }

            @Override
            public Image getImage(final Object object) {
                if (itemPropertyDescriptor != null) {
                    return ExtendedImageRegistry.getInstance().getImage(itemPropertyDescriptor.getLabelProvider(object).getImage(object));
                } else {
                    return null;
                }
            }
        };
    }

    /**
     * Return the propertyDescriptor corresponding to the feature of this column
     * for the instance
     * 
     * @param instance
     *            the instance.
     * @return the propertyDescriptor corresponding to the feature of this
     *         column for the instance or null
     */
    private IItemPropertyDescriptor getPropertyDescriptor(final EObject instance) {
        if (instance == null) {
            return null;
        }
        final EStructuralFeature structuralFeature = instance.eClass().getEStructuralFeature(getFeatureName());
        final IItemPropertySource propertySource = (IItemPropertySource) adapterFactory.adapt(instance, IItemPropertySource.class);
        IItemPropertyDescriptor propertyDescriptor = null;
        if (propertySource != null) {
            final Iterator<?> iterDescriptors = propertySource.getPropertyDescriptors(instance).iterator();
            while (iterDescriptors.hasNext() && propertyDescriptor == null) {
                final IItemPropertyDescriptor currentPropertyDescriptor = (IItemPropertyDescriptor) iterDescriptors.next();
                final Object currentFeature = currentPropertyDescriptor.getFeature(instance);
                if (currentFeature != null && currentFeature.equals(structuralFeature)) {
                    propertyDescriptor = currentPropertyDescriptor;
                }
            }
        }
        return propertyDescriptor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#initializeCellEditorValue(org.eclipse.jface.viewers.CellEditor,
     *      org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        if (((DTableTreeViewer) getViewer()).getFirstEditionCharacter() != null) {
            cellEditor.setValue(((DTableTreeViewer) getViewer()).getFirstEditionCharacter().toString());
        } else {
            super.initializeCellEditorValue(cellEditor, cell);
        }
    }

    private class StandardSetValueRecordingCommand extends RecordingCommand {

        private DCell cell;

        private Object value;

        public StandardSetValueRecordingCommand(TransactionalEditingDomain domain, String label, DCell cell, Object value) {
            super(domain, label);
            this.cell = cell;
            this.value = value;
        }

        @Override
        protected void doExecute() {
            try {
                if ((getAccessor().eGet(cell.getTarget(), getFeatureName()) == null && value != null)
                        || (getAccessor().eGet(cell.getTarget(), getFeatureName()) != null && !getAccessor().eGet(cell.getTarget(), getFeatureName()).equals(value))) {
                    if (isMany(cell.getTarget())) {
                        getAccessor().eClear(cell.getTarget(), getFeatureName());
                    }
                    getAccessor().eSet(cell.getTarget(), getFeatureName(), value);
                }
            } catch (final LockedInstanceException e) {
                SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
            } catch (final FeatureNotFoundException e) {
                RuntimeLoggerManager.INSTANCE.error(featureColumn, TablePackage.eINSTANCE.getDFeatureColumn_FeatureName(), e);
            }
        }

    }
}
