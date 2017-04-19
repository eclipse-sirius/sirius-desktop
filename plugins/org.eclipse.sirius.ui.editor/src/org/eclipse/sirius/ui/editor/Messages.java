/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SessionEditorPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String UI_SessionEditor_header_title;

    @TranslatableMessage
    public static String UI_SessionEditor_page_loading_error_message;

    @TranslatableMessage
    public static String UI_SessionEditor_session_loading_error_message;

    @TranslatableMessage
    public static String UI_SessionEditor_session_loading_task_title;

    @TranslatableMessage
    public static String UI_SessionEditor_default_page_tab_label;

    @TranslatableMessage
    public static String UI_SessionEditor_models_title;

    @TranslatableMessage
    public static String UI_SessionEditor_models_button_removeSemanticModel;

    @TranslatableMessage
    public static String UI_SessionEditor_models_button_newSemanticModel;

    @TranslatableMessage
    public static String UI_SessionEditor_new_semantic_model_action_label;

    @TranslatableMessage
    public static String UI_SessionEditor_viewpoints_title;

    @TranslatableMessage
    public static String UI_SessionEditor_representation_title;

    @TranslatableMessage
    public static String UI_SessionEditor_representation_button_removeRepresentation;

    @TranslatableMessage
    public static String UI_SessionEditor_representation_button_newRepresentation;

    @TranslatableMessage
    public static String UI_SessionEditor_inputNotHandled_error_message;

    @TranslatableMessage
    public static String GraphicalRepresentationHandler_missingDependencies_requirements;

    @TranslatableMessage
    public static String GraphicalRepresentationHandler_disabledViewpoint_label;

    @TranslatableMessage
    public static String GraphicalRepresentationHandler_representationNumber_label;

    @TranslatableMessage
    public static String DefaultSessionEditorPage_selectFilterAction_tooltip;

    @TranslatableMessage
    public static String DefaultSessionEditorPage_collapseAllAction_tooltip;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
