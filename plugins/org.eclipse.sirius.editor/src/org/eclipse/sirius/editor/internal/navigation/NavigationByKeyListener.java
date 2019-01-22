/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.internal.navigation;

import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.ui.tools.api.assist.ContentProposalClient;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Text;

/**
 * Listen to F3 key hit when the cursor has focus on VSM expression and propagate the event to
 * {@link INavigatorFromVSMExpression} of the current {@link NavigationFromVSMExpressionRegistry}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class NavigationByKeyListener implements KeyListener {

    /**
     * The VSM object edited.
     */
    private EObject targetObject;

    /**
     * The text widget inside the property section that contains the expression string.
     */
    private Text textWidget;

    /**
     * The property section displaying the VSM expression.
     */
    private AbstractViewpointPropertySection propertySection;

    /**
     * Constructor.
     * 
     * @param propertySection
     *            the property section containing the VSM expression.
     * @param textWidget
     *            the widget containing the VSM expression.
     * @param targetObject
     *            the {@link EObject} containing the VSM expression.
     */
    public NavigationByKeyListener(AbstractViewpointPropertySection propertySection, Text textWidget, EObject targetObject) {
        this.targetObject = targetObject;
        this.textWidget = textWidget;
        this.propertySection = propertySection;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.keyCode == SWT.F3) {
            NavigationFromVSMExpressionRegistry navigationRegistry = SiriusEditorPlugin.getPlugin().getNavigationRegistry();
            Set<Supplier<INavigatorFromVSMExpression>> navigators = navigationRegistry.getNavigators();
            for (Supplier<INavigatorFromVSMExpression> navigatorSupplier : navigators) {
                INavigatorFromVSMExpression navigator = navigatorSupplier.get();
                IInterpreterContext interContext = SiriusInterpreterContextFactory.createInterpreterContext(targetObject, ((ContentProposalClient) propertySection).getFeature());
                ContentContext contentContext = new ContentContext(textWidget.getText(), textWidget.getCaretPosition(), interContext);
                if (navigator.doProvideNavigationFor(propertySection, targetObject, contentContext)) {
                    navigator.triggerNavigation(propertySection, targetObject, contentContext);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }

}
