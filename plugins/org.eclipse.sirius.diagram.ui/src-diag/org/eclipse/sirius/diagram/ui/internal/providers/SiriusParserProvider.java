/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.internal.parsers.MessageFormatParser;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * @was-generated
 */
public class SiriusParserProvider extends AbstractProvider implements IParserProvider {

    /**
     * @was-generated
     */
    private IParser dNodeName_5002Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeName_5002Parser() {
        if (dNodeName_5002Parser == null) {
            dNodeName_5002Parser = createDNodeName_5002Parser();
        }
        return dNodeName_5002Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeName_5002Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeContainerName_5006Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeContainerName_5006Parser() {
        if (dNodeContainerName_5006Parser == null) {
            dNodeContainerName_5006Parser = createDNodeContainerName_5006Parser();
        }
        return dNodeContainerName_5006Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeContainerName_5006Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeListName_5007Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeListName_5007Parser() {
        if (dNodeListName_5007Parser == null) {
            dNodeListName_5007Parser = createDNodeListName_5007Parser();
        }
        return dNodeListName_5007Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeListName_5007Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeName_5001Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeName_5001Parser() {
        if (dNodeName_5001Parser == null) {
            dNodeName_5001Parser = createDNodeName_5001Parser();
        }
        return dNodeName_5001Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeName_5001Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeName_5003Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeName_5003Parser() {
        if (dNodeName_5003Parser == null) {
            dNodeName_5003Parser = createDNodeName_5003Parser();
        }
        return dNodeName_5003Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeName_5003Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeContainerName_5005Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeContainerName_5005Parser() {
        if (dNodeContainerName_5005Parser == null) {
            dNodeContainerName_5005Parser = createDNodeContainerName_5005Parser();
        }
        return dNodeContainerName_5005Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeContainerName_5005Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeListName_5004Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeListName_5004Parser() {
        if (dNodeListName_5004Parser == null) {
            dNodeListName_5004Parser = createDNodeListName_5004Parser();
        }
        return dNodeListName_5004Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeListName_5004Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeListElement_3010Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeListElement_3010Parser() {
        if (dNodeListElement_3010Parser == null) {
            dNodeListElement_3010Parser = createDNodeListElement_3010Parser();
        }
        return dNodeListElement_3010Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeListElement_3010Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dNodeName_5010Parser;

    /**
     * @was-generated
     */
    private IParser getDNodeName_5010Parser() {
        if (dNodeName_5010Parser == null) {
            dNodeName_5010Parser = createDNodeName_5010Parser();
        }
        return dNodeName_5010Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDNodeName_5010Parser() {
        return createDRepresentationElementNameParser();
    }

    /**
     * @was-generated
     */
    private IParser dEdgeName_6001Parser;

    /**
     * @was-generated
     */
    private IParser getDEdgeName_6001Parser() {
        if (dEdgeName_6001Parser == null) {
            dEdgeName_6001Parser = createDEdgeName_6001Parser();
        }
        return dEdgeName_6001Parser;
    }

    /**
     * @was-generated
     */
    private IParser dEdgeName_6002Parser;

    private IParser getDEdgeName_6002Parser() {
        if (dEdgeName_6002Parser == null) {
            dEdgeName_6002Parser = createDEdgeName_6002Parser();
        }
        return dEdgeName_6002Parser;
    }

    /**
     * @was-generated
     */
    private IParser dEdgeName_6003Parser;

    private IParser getDEdgeName_6003Parser() {
        if (dEdgeName_6003Parser == null) {
            dEdgeName_6003Parser = createDEdgeName_6003Parser();
        }
        return dEdgeName_6003Parser;
    }

    /**
     * @was-generated
     */
    protected IParser createDEdgeName_6001Parser() {
        return createDRepresentationElementNameParser();
    }

    protected IParser createDEdgeName_6002Parser() {
        EAttribute[] features = new EAttribute[] { DiagramPackage.eINSTANCE.getDEdge_BeginLabel(), };
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    protected IParser createDEdgeName_6003Parser() {
        EAttribute[] features = new EAttribute[] { DiagramPackage.eINSTANCE.getDEdge_EndLabel(), };
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    protected IParser createDRepresentationElementNameParser() {
        EAttribute[] features = new EAttribute[] { ViewpointPackage.eINSTANCE.getDRepresentationElement_Name(), };
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    /**
     * @was-generated
     */
    protected IParser getParser(int visualID) {
        switch (visualID) {
        case NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID:
            return getDNodeName_5002Parser();
        case DNodeContainerNameEditPart.VISUAL_ID:
            return getDNodeContainerName_5006Parser();
        case DNodeListNameEditPart.VISUAL_ID:
            return getDNodeListName_5007Parser();
        case NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID:
            return getDNodeName_5001Parser();
        case NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID:
            return getDNodeName_5003Parser();
        case DNodeContainerName2EditPart.VISUAL_ID:
            return getDNodeContainerName_5005Parser();
        case DNodeListName2EditPart.VISUAL_ID:
            return getDNodeListName_5004Parser();
        case DNodeListElementEditPart.VISUAL_ID:
            return getDNodeListElement_3010Parser();
        case NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID:
            return getDNodeName_5010Parser();
        case DEdgeNameEditPart.VISUAL_ID:
            return getDEdgeName_6001Parser();
        case DEdgeEndNameEditPart.VISUAL_ID:
            return getDEdgeName_6003Parser();
        case DEdgeBeginNameEditPart.VISUAL_ID:
            return getDEdgeName_6002Parser();
        }
        return null;
    }

    /**
     * @was-generated
     */
    @Override
    public IParser getParser(IAdaptable hint) {
        String vid = hint.getAdapter(String.class);
        if (vid != null) {
            return getParser(SiriusVisualIDRegistry.getVisualID(vid));
        }
        View view = hint.getAdapter(View.class);
        if (view != null) {
            return getParser(SiriusVisualIDRegistry.getVisualID(view));
        }
        return null;
    }

    /**
     * @was-generated
     */
    @Override
    public boolean provides(IOperation operation) {
        if (operation instanceof GetParserOperation) {
            IAdaptable hint = ((GetParserOperation) operation).getHint();
            if (SiriusElementTypes.getElement(hint) == null) {
                return false;
            }
            return getParser(hint) != null;
        }
        return false;
    }

    /**
     * @was-generated
     */
    public static class HintAdapter extends ParserHintAdapter {

        /**
         * @was-generated
         */
        private final IElementType elementType;

        /**
         * @was-generated
         */
        public HintAdapter(IElementType type, EObject object, String parserHint) {
            super(object, parserHint);
            assert type != null;
            elementType = type;
        }

        /**
         * @was-generated
         */
        @Override
        public Object getAdapter(Class adapter) {
            if (IElementType.class.equals(adapter)) {
                return elementType;
            }
            return super.getAdapter(adapter);
        }
    }

}
