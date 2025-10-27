/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * A Test class to test the {@link CanonicalDBorderItemLocator}.
 * 
 * @author fbarbin
 * 
 */
public class CanonicalDBorderItemLocatorTest extends SiriusDiagramTestCase {

    private String DESCRIPTION = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4184/My.odesign";

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4184/My.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4184/My.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL, DESCRIPTION, AIRD);
    }

    /**
     * This test aims at checking that we do not detect conflict with nodes when
     * the GMF visibility is true but not the DDiagramElement visibility.
     */
    public void testVisibilityConsistency() {
        DView view = session.getOwnedViews().iterator().next();
        DDiagram ddiagram = (DDiagram) new DViewQuery(view).getLoadedRepresentations().get(0);
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(ddiagram);
        Option<Diagram> option = query.getAssociatedGMFDiagram();
        if (option.some()) {
            Iterator<EObject> iterator = Iterators.filter(option.get().eAllContents(), new Predicate<EObject>() {
                @Override
                public boolean apply(EObject arg0) {
                    if (arg0 instanceof Node) {
                        int typeInt = SiriusVisualIDRegistry.getVisualID(((Node) arg0).getType());
                        if (typeInt == DNode4EditPart.VISUAL_ID) {
                            EObject element = ((Node) arg0).getElement();
                            // we get the only one visible border node (other
                            // border nodes based on EClass are masked)
                            return ((DDiagramElement) element).getTarget() instanceof EEnum;
                        }
                    }
                    return false;
                }
            });

            while (iterator.hasNext()) {
                Node node = (Node) iterator.next();
                CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator((Node) node.eContainer(), PositionConstants.NSEW);
                locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                Rectangle expectedBounds = GMFHelper.getAbsoluteBounds(node, false, false, false, true);
                Point location = locator.getValidLocation(expectedBounds, node, Collections.singletonList(node));
                assertEquals("The border node should not be in conflict", expectedBounds.getLocation(), location);
            }
        } else {
            fail("No GMF diagram found");
        }
    }
}
