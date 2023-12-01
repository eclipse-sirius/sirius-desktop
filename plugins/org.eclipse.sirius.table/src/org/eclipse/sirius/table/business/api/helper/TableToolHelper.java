/*******************************************************************************
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.api.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Helper for tool of Tables.
 * 
 * @author nperansin
 */
public class TableToolHelper {

    private final ModelAccessor accessor;
    
    /**
     * Constructor with model accessor.
     * 
     * @param accessor ModelAccessor
     */
    public TableToolHelper(ModelAccessor accessor) {
        this.accessor = accessor;
    }

    private IPermissionAuthority getAuthority() {
        return accessor.getPermissionAuthority();
    }
    
    /**
     * Evaluates if a cell can be edited.
     * 
     * @param line container of the cell
     * @param column column of the cell
     * @return true if can be edited.
     */
    public boolean canEdit(DLine line, DFeatureColumn column) {

        Option<DCell> optCell = TableHelper.getCell(line, column);
        if (!optCell.some()) {
            return false;
        }
        boolean canEdit = true;
        DCell cell = optCell.get();
        
        CellUpdater updater = cell.getUpdater();
        if (updater != null && !StringUtil.isEmpty(updater.getCanEdit())) {
            canEdit = InterpretationContext.with(cell.getTarget(), ctx -> {
                ctx.setVariables(TableVariablesHelper.getVariables(line, column));
                
                // Legacy
                ctx.setVariable(IInterpreterSiriusVariables.VIEWPOINT, TableHelper.getTable(line));
                
                ctx.setVariable(IInterpreterSiriusVariables.ELEMENT, line.getTarget());
                
                return ctx.getInterpreter().evaluateBoolean(cell.getTarget(), updater, 
                        DescriptionPackage.eINSTANCE.getCellUpdater_CanEdit());
            });
        }
        return canEdit 
                && getAuthority().canEditFeature(cell.getTarget(), column.getFeatureName()) 
                && getAuthority().canEditInstance(line);
    }
    
    /**
     * Evaluates if a cell can be edited.
     * <p>
     * If a cell exists, authority on target and CanEdit are evaluated. <br/>
     * If creation tool exists, authority on line and column and precondition are evaluated. <br/>
     * Otherwise cell cannot be edited.
     * </p>
     * 
     * @param line container of the cell
     * @param column column of the cell
     * @return true if can be edited.
     */
    public boolean canEdit(DLine line, DTargetColumn column) {
        final Option<DCell> optionalCell = TableHelper.getCell(line, column);
        if (!optionalCell.some()) {
            return canCreateCell(line, column);
        }
        
        // See org.eclipse.sirius.table.tools.internal.command.TableCommandFactory#buildCommandFromCell(DCell, TableTool, Object)
        // for context setting.
        EObject target = optionalCell.get().getTarget();
        final EObject interpreterContext;
        if (target != null) {
            interpreterContext = target;
        } else {
            interpreterContext = optionalCell.get().getLine().getTarget();
        }
        
        boolean canEdit = false;
        CellUpdater updater = optionalCell.get().getUpdater();
        if (updater != null && updater.getDirectEdit() != null) {
            canEdit = StringUtil.isEmpty(updater.getCanEdit()) 
                    ||  InterpretationContext.with(optionalCell.get(), ctx -> {
                        ctx.setVariables(TableVariablesHelper.getVariables(line, column));
                        // Legacy
                        ctx.setVariable(IInterpreterSiriusVariables.VIEWPOINT, TableHelper.getTable(line));

                        return ctx.getInterpreter().evaluateBoolean(interpreterContext, updater, 
                                DescriptionPackage.eINSTANCE.getCellUpdater_CanEdit());
                    });
        }
        
        return canEdit && getAuthority().canEditInstance(optionalCell.get().getTarget());
    }
    
    private boolean canCreateCell(DLine line, DTargetColumn column) {
        Option<CreateCellTool> tool = TableHelper.getCreateCellTool(line, column);
        if (!tool.some()) {
            return false;
        }
        
        // For evaluation, 'self' must be as in 
        // org.eclipse.sirius.table.tools.internal.command.TableCommandFactory#buildCommandFromIntersection(DLine, DTargetColumn, CreateCellTool, Object)
        EObject target = line.getTarget();
        
        boolean canEdit = StringUtil.isEmpty(tool.get().getPrecondition())
                || InterpretationContext.with(target, ctx -> {
                    ctx.setVariables(TableVariablesHelper.getVariables(line, column));

                    // Legacy
                    ctx.setVariable(IInterpreterSiriusVariables.VIEWPOINT, TableHelper.getTable(line));

                    return ctx.getInterpreter().evaluateBoolean(target, tool.get(), 
                            ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
                });
        
        
        return canEdit && getAuthority().canEditInstance(line) && getAuthority().canEditInstance(column);
    }
    
    /**
     * Evaluates if a tool is enable for the selection.
     * <p>
     * Selection may be line, column or whole table.
     * </p>
     * 
     * @param <TT> tool with precondition
     * @param tool to evaluate
     * @param selection decorator
     * @return true if valid and enable
     */
    public static <TT extends TableTool & AbstractToolDescription> boolean isAxisToolEnable(TT tool, DSemanticDecorator selection) {
        // is properly set
        boolean valid = tool != null && tool.getFirstModelOperation() != null;
        if (valid && hasPrecondition(tool)) {
            return InterpretationContext.with(selection.getTarget(), ctxt -> {
                ctxt.setVariables(TableVariablesHelper.getVariables(selection));
                return ctxt.getInterpreter().evaluateBoolean(selection.getTarget(), tool, 
                        ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
            });
        }
        return valid;
    }
    
    private static boolean hasPrecondition(AbstractToolDescription tool) {
        return !StringUtil.isEmpty(tool.getPrecondition());
    }
}
