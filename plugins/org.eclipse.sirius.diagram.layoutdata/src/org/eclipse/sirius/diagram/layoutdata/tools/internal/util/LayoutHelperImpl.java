/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.layoutdata.tools.internal.util;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.Point;
import org.eclipse.sirius.diagram.layoutdata.tools.Messages;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.EdgeConfiguration;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.NodeConfiguration;

/**
 * Helper to manage the layout data.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LayoutHelperImpl implements LayoutHelper {

    /**
     * Basic implementation.
     * 
     * @author dlecan
     */
    private static final class LayoutDifferenceImpl<T> implements LayoutDifference<T> {

        private final T leftElement;

        private final T rightElement;

        private final Configuration configuration;

        private LayoutDifferenceImpl(final T leftElement, final T rightElement, Configuration configuration) {
            this.leftElement = leftElement;
            this.rightElement = rightElement;
            this.configuration = configuration;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getMessage() {
            return MessageFormat.format(Messages.LayoutHelperImpl_layoutDifferenceMessage, configuration, elementToString(leftElement), elementToString(rightElement));
        }

        private String elementToString(T element) {
            String elementToString;
            if (leftElement instanceof EObject) {
                elementToString = LayoutHelperImpl.toString((EObject) element);
            } else {
                elementToString = element.toString();
            }
            return elementToString;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T getLeftElement() {
            return leftElement;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T getRightElement() {
            return rightElement;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return getMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean haveSameLayout(final NodeLayoutData nodeLayout1, final NodeLayoutData nodeLayout2, final Configuration configuration) {
        return doHaveSameLayout(nodeLayout1, nodeLayout2, configuration) == null;
    }

    private <T extends AbstractLayoutData> LayoutDifference<T> doAbstractLayoutDataHaveSameLayout(final T abstractLayout1, final T abstractLayout2, final Configuration configuration) {
        LayoutDifference<T> result = null;
        boolean haveSameLayout = false;

        if (abstractLayout1 == abstractLayout2) {
            haveSameLayout = true;
        } else if (abstractLayout1 != null && abstractLayout2 != null) {
            haveSameLayout = abstractLayout1.getId() != null;
            haveSameLayout = haveSameLayout && abstractLayout1.getId().equals(abstractLayout2.getId());
            haveSameLayout = haveSameLayout && haveSameLayout(abstractLayout1.getLabel(), abstractLayout2.getLabel(), configuration);
        }
        if (!haveSameLayout && result == null) {
            result = new LayoutDifferenceImpl<T>(abstractLayout1, abstractLayout2, configuration);
        }
        return result;
    }

    private LayoutDifference<? extends AbstractLayoutData> doHaveSameLayout(final NodeLayoutData nodeLayout1, final NodeLayoutData nodeLayout2, final Configuration configuration) {
        LayoutDifference<? extends AbstractLayoutData> result = null;
        boolean haveSameLayout = false;

        LayoutDifference<NodeLayoutData> abstractDataHaveSameLayout = doAbstractLayoutDataHaveSameLayout(nodeLayout1, nodeLayout2, configuration);

        haveSameLayout = abstractDataHaveSameLayout == null;

        // If they have the same basic layout,
        // - either the 2 nodes are both not null
        // => check inner data
        // - or both null
        // => nothing to check, same layout
        if (haveSameLayout && nodeLayout1 != null && nodeLayout2 != null) {

            NodeConfiguration nodeConfiguration = configuration.getNodeConfiguration();

            haveSameLayout = haveSameLayout && isAroundPoint(nodeLayout1.getLocation(), nodeLayout2.getLocation(), nodeConfiguration.getDistanceAroundPoint());
            haveSameLayout = haveSameLayout && nodeLayout1.getWidth() == nodeLayout2.getWidth();
            haveSameLayout = haveSameLayout && nodeLayout1.getHeight() == nodeLayout2.getHeight();

            if (haveSameLayout && configuration.isRecursive()) {

                haveSameLayout = nodeLayout1.getChildren().size() == nodeLayout2.getChildren().size();
                haveSameLayout = haveSameLayout && nodeLayout1.getOutgoingEdges().size() == nodeLayout2.getOutgoingEdges().size();

                if (haveSameLayout) {
                    for (int i = 0; i < nodeLayout1.getChildren().size() && haveSameLayout; i++) {
                        result = doHaveSameLayout(nodeLayout1.getChildren().get(i), nodeLayout2.getChildren().get(i), configuration);
                        haveSameLayout = result == null;
                    }
                    for (int i = 0; i < nodeLayout1.getOutgoingEdges().size() && haveSameLayout; i++) {
                        result = doHaveSameLayout(nodeLayout1.getOutgoingEdges().get(i), nodeLayout2.getOutgoingEdges().get(i), configuration);
                        haveSameLayout = result == null;
                    }
                }
            }
            if (!haveSameLayout && result == null) {
                result = new LayoutDifferenceImpl<NodeLayoutData>(nodeLayout1, nodeLayout2, configuration);
            }
        } else {
            result = abstractDataHaveSameLayout;
        }

        return result;
    }

    /**
     * Check if real point is around expected point.
     * <p>
     * Distance between the 2 points must be less or equal than provided
     * distance.
     * </p>
     * 
     * @param location
     *            The expected location
     * @param realLocation
     *            The real location
     * @param maxDistance
     *            the error margin in x and y coordinates.
     * @return true is the real location is near the expected location, false
     *         otherwise
     */
    private boolean isAroundPoint(Point expectedLocation, Point realLocation, double maxDistance) {
        long dx = realLocation.getX() - expectedLocation.getX();
        long dy = realLocation.getY() - expectedLocation.getY();
        long distance2 = dx * dx + dy * dy;
        return distance2 <= (maxDistance * maxDistance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutDifference<?> computeFirstLayoutDifference(final Collection<? extends EObject> col1, final Collection<? extends EObject> col2, final Configuration configuration) {

        LayoutDifference<?> result = null;

        boolean haveSameLayout = false;

        if (col1 == col2) {
            haveSameLayout = true;
        } else {
            haveSameLayout = col1 != null && col2 != null && col1.size() == col2.size();
            if (haveSameLayout) {
                final Iterator<? extends EObject> it1 = col1.iterator();
                final Iterator<? extends EObject> it2 = col2.iterator();

                while (haveSameLayout && it1.hasNext() && it2.hasNext()) {
                    final EObject layoutData1 = it1.next();
                    final EObject layoutData2 = it2.next();

                    if (layoutData1 instanceof NodeLayoutData && layoutData2 instanceof NodeLayoutData) {
                        result = doHaveSameLayout((NodeLayoutData) layoutData1, (NodeLayoutData) layoutData2, configuration);
                    } else if (layoutData1 instanceof EdgeLayoutData && layoutData2 instanceof EdgeLayoutData) {
                        result = doHaveSameLayout((EdgeLayoutData) layoutData1, (EdgeLayoutData) layoutData2, configuration);
                    }
                    haveSameLayout = result == null;
                }
            }
        }

        if (!haveSameLayout && result == null) {
            result = new LayoutDifferenceImpl<Collection<? extends EObject>>(col1, col2, configuration);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean haveSameLayout(final Collection<? extends EObject> col1, final Collection<? extends EObject> col2, final Configuration configuration) {
        return computeFirstLayoutDifference(col1, col2, configuration) == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean haveSameLayout(final EdgeLayoutData edgeLayout1, final EdgeLayoutData edgeLayout2, Configuration configuration) {
        return doHaveSameLayout(edgeLayout1, edgeLayout2, configuration) == null;
    }

    private LayoutDifference<EdgeLayoutData> doHaveSameLayout(final EdgeLayoutData edgeLayout1, final EdgeLayoutData edgeLayout2, Configuration configuration) {
        LayoutDifference<EdgeLayoutData> result = null;
        boolean haveSameLayout = false;

        LayoutDifference<EdgeLayoutData> abstractDataHaveSameLayout = doAbstractLayoutDataHaveSameLayout(edgeLayout1, edgeLayout2, configuration);

        haveSameLayout = abstractDataHaveSameLayout == null;

        // If they have the same basic layout,
        // - either the 2 nodes are both not null
        // => check inner data
        // - or both null
        // => nothing to check, same layout
        if (haveSameLayout && edgeLayout1 != null && edgeLayout2 != null) {

            EdgeConfiguration edgeConfiguration = configuration.getEdgeConfiguration();

            haveSameLayout = haveSameLayout && StringUtil.equals(edgeLayout1.getSourceTerminal(), edgeLayout2.getSourceTerminal());
            haveSameLayout = haveSameLayout && StringUtil.equals(edgeLayout1.getTargetTerminal(), edgeLayout2.getTargetTerminal());

            haveSameLayout = haveSameLayout && edgeLayout1.getRouting() == edgeLayout2.getRouting();
            haveSameLayout = haveSameLayout && isAroundPoint(edgeLayout1.getSourceRefPoint(), edgeLayout2.getSourceRefPoint(), edgeConfiguration.getDistanceAroundPointsOfEdgeBendpointsList());

            haveSameLayout = haveSameLayout && edgeLayout1.getPointList().size() == edgeLayout2.getPointList().size();

            if (haveSameLayout) {
                for (int i = 0; i < edgeLayout1.getPointList().size() && haveSameLayout; i++) {
                    haveSameLayout = isAroundPoint(edgeLayout1.getPointList().get(i), edgeLayout2.getPointList().get(i), edgeConfiguration.getDistanceAroundPointsOfEdgeBendpointsList());
                }
            }

            if (!haveSameLayout) {
                result = new LayoutDifferenceImpl<EdgeLayoutData>(edgeLayout1, edgeLayout2, configuration);
            }
        } else {
            result = abstractDataHaveSameLayout;
        }

        return result;
    }

    private static String toString(EObject eobject) {
        return LayoutHelperImpl.toString("\n", eobject); //$NON-NLS-1$
    }

    private static String toString(String prefix, EObject eobject) {
        StringBuilder sb = new StringBuilder();
        EClass eClass = eobject.eClass();
        int i;
        int size = eClass.getFeatureCount();
        for (i = 0; i < size; ++i) {
            // Ignore derived features.
            //
            EStructuralFeature feature = eClass.getEStructuralFeature(i);
            if (!feature.isDerived()) {
                sb.append(prefix);
                sb.append(feature.getName());
                sb.append(" : "); //$NON-NLS-1$
                Object obj = eobject.eGet(feature);
                if (obj == null) {
                    sb.append("null"); //$NON-NLS-1$
                } else if (obj instanceof EObject) {
                    sb.append(LayoutHelperImpl.toString("\n     ", (EObject) obj)); //$NON-NLS-1$
                } else if (obj instanceof List<?>) {
                    List<?> list = (List<?>) obj;
                    if (!list.isEmpty()) {
                        sb.append('\n');
                        sb.append("     "); //$NON-NLS-1$
                    }
                    for (Object object : list) {
                        sb.append('\n');
                        sb.append("     "); //$NON-NLS-1$
                        if (object instanceof EObject) {
                            sb.append(LayoutHelperImpl.toString((EObject) object));
                        } else {
                            sb.append(object.toString());
                        }
                    }
                } else {
                    sb.append(obj.toString());
                }
            }
        }
        return sb.toString();
    }
}
