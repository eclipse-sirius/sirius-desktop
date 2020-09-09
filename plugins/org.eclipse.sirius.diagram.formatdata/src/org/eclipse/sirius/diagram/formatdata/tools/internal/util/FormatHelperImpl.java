/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.formatdata.tools.internal.util;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;
import org.eclipse.sirius.diagram.formatdata.tools.Messages;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.EdgeConfiguration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.NodeConfiguration;

/**
 * Helper to manage the format data.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class FormatHelperImpl implements FormatHelper {

    /**
     * Basic implementation.
     * 
     * @author dlecan
     */
    private static final class FormatDifferenceImpl<T> implements FormatDifference<T> {

        private final T leftElement;

        private final T rightElement;

        private final Configuration configuration;

        private FormatDifferenceImpl(final T leftElement, final T rightElement, Configuration configuration) {
            this.leftElement = leftElement;
            this.rightElement = rightElement;
            this.configuration = configuration;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getMessage() {
            return MessageFormat.format(Messages.FormatHelperImpl_formatDifferenceMessage, configuration, elementToString(leftElement), elementToString(rightElement));
        }

        private String elementToString(T element) {
            String elementToString;
            if (leftElement instanceof EObject) {
                elementToString = FormatHelperImpl.toString((EObject) element);
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
    public boolean haveSameLayout(final NodeFormatData nodeFormat1, final NodeFormatData nodeFormat2, final Configuration configuration) {
        return doHaveSameLayout(nodeFormat1, nodeFormat2, configuration) == null;
    }

    private <T extends AbstractFormatData> FormatDifference<T> doAbstractFormatDataHaveSameLayout(final T abstractFormat1, final T abstractFormat2, final Configuration configuration) {
        FormatDifference<T> result = null;
        boolean haveSameLayout = false;

        if (abstractFormat1 == abstractFormat2) {
            haveSameLayout = true;
        } else if (abstractFormat1 != null && abstractFormat2 != null) {
            haveSameLayout = abstractFormat1.getId() != null;
            haveSameLayout = haveSameLayout && abstractFormat1.getId().equals(abstractFormat2.getId());
            haveSameLayout = haveSameLayout && haveSameLayout(abstractFormat1.getLabel(), abstractFormat2.getLabel(), configuration);
        }
        if (!haveSameLayout && result == null) {
            result = new FormatDifferenceImpl<T>(abstractFormat1, abstractFormat2, configuration);
        }
        return result;
    }

    private FormatDifference<? extends AbstractFormatData> doHaveSameLayout(final NodeFormatData nodeFormat1, final NodeFormatData nodeFormat2, final Configuration configuration) {
        FormatDifference<? extends AbstractFormatData> result = null;
        boolean haveSameLayout = false;

        FormatDifference<NodeFormatData> abstractDataHaveSameLayout = doAbstractFormatDataHaveSameLayout(nodeFormat1, nodeFormat2, configuration);

        haveSameLayout = abstractDataHaveSameLayout == null;

        // If they have the same basic format,
        // - either the 2 nodes are both not null
        // => check inner data
        // - or both null
        // => nothing to check, same format
        if (haveSameLayout && nodeFormat1 != null && nodeFormat2 != null) {

            NodeConfiguration nodeConfiguration = configuration.getNodeConfiguration();

            haveSameLayout = haveSameLayout && isAroundPoint(nodeFormat1.getLocation(), nodeFormat2.getLocation(), nodeConfiguration.getDistanceAroundPoint());
            haveSameLayout = haveSameLayout && nodeFormat1.getWidth() == nodeFormat2.getWidth();
            haveSameLayout = haveSameLayout && nodeFormat1.getHeight() == nodeFormat2.getHeight();

            if (haveSameLayout && configuration.isRecursive()) {

                haveSameLayout = nodeFormat1.getChildren().size() == nodeFormat2.getChildren().size();
                haveSameLayout = haveSameLayout && nodeFormat1.getOutgoingEdges().size() == nodeFormat2.getOutgoingEdges().size();

                if (haveSameLayout) {
                    for (int i = 0; i < nodeFormat1.getChildren().size() && haveSameLayout; i++) {
                        result = doHaveSameLayout(nodeFormat1.getChildren().get(i), nodeFormat2.getChildren().get(i), configuration);
                        haveSameLayout = result == null;
                    }
                    for (int i = 0; i < nodeFormat1.getOutgoingEdges().size() && haveSameLayout; i++) {
                        result = doHaveSameLayout(nodeFormat1.getOutgoingEdges().get(i), nodeFormat2.getOutgoingEdges().get(i), configuration);
                        haveSameLayout = result == null;
                    }
                }
            }
            if (!haveSameLayout && result == null) {
                result = new FormatDifferenceImpl<NodeFormatData>(nodeFormat1, nodeFormat2, configuration);
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
    public FormatDifference<?> computeFirstFormatDifference(final Collection<? extends EObject> col1, final Collection<? extends EObject> col2, final Configuration configuration) {

        FormatDifference<?> result = null;

        boolean haveSameLayout = false;

        if (col1 == col2) {
            haveSameLayout = true;
        } else {
            haveSameLayout = col1 != null && col2 != null && col1.size() == col2.size();
            if (haveSameLayout) {
                final Iterator<? extends EObject> it1 = col1.iterator();
                final Iterator<? extends EObject> it2 = col2.iterator();

                while (haveSameLayout && it1.hasNext() && it2.hasNext()) {
                    final EObject formatData1 = it1.next();
                    final EObject formatData2 = it2.next();

                    if (formatData1 instanceof NodeFormatData && formatData2 instanceof NodeFormatData) {
                        result = doHaveSameLayout((NodeFormatData) formatData1, (NodeFormatData) formatData2, configuration);
                    } else if (formatData1 instanceof EdgeFormatData && formatData2 instanceof EdgeFormatData) {
                        result = doHaveSameLayout((EdgeFormatData) formatData1, (EdgeFormatData) formatData2, configuration);
                    }
                    haveSameLayout = result == null;
                }
            }
        }

        if (!haveSameLayout && result == null) {
            result = new FormatDifferenceImpl<Collection<? extends EObject>>(col1, col2, configuration);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean haveSameLayout(final Collection<? extends EObject> col1, final Collection<? extends EObject> col2, final Configuration configuration) {
        return computeFirstFormatDifference(col1, col2, configuration) == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean haveSameLayout(final EdgeFormatData edgeFormat1, final EdgeFormatData edgeFormat2, Configuration configuration) {
        return doHaveSameLayout(edgeFormat1, edgeFormat2, configuration) == null;
    }

    private FormatDifference<EdgeFormatData> doHaveSameLayout(final EdgeFormatData edgeFormat1, final EdgeFormatData edgeFormat2, Configuration configuration) {
        FormatDifference<EdgeFormatData> result = null;
        boolean haveSameLayout = false;

        FormatDifference<EdgeFormatData> abstractDataHaveSameLayout = doAbstractFormatDataHaveSameLayout(edgeFormat1, edgeFormat2, configuration);

        haveSameLayout = abstractDataHaveSameLayout == null;

        // If they have the same basic format,
        // - either the 2 nodes are both not null
        // => check inner data
        // - or both null
        // => nothing to check, same format
        if (haveSameLayout && edgeFormat1 != null && edgeFormat2 != null) {

            EdgeConfiguration edgeConfiguration = configuration.getEdgeConfiguration();

            haveSameLayout = haveSameLayout && StringUtil.equals(edgeFormat1.getSourceTerminal(), edgeFormat2.getSourceTerminal());
            haveSameLayout = haveSameLayout && StringUtil.equals(edgeFormat1.getTargetTerminal(), edgeFormat2.getTargetTerminal());

            haveSameLayout = haveSameLayout && edgeFormat1.getRouting() == edgeFormat2.getRouting();
            if (edgeFormat1.getSourceRefPoint() != null && edgeFormat2.getSourceRefPoint() != null) {
                haveSameLayout = haveSameLayout && isAroundPoint(edgeFormat1.getSourceRefPoint(), edgeFormat2.getSourceRefPoint(), edgeConfiguration.getDistanceAroundPointsOfEdgeBendpointsList());
            }

            haveSameLayout = haveSameLayout && edgeFormat1.getPointList().size() == edgeFormat2.getPointList().size();

            if (haveSameLayout) {
                for (int i = 0; i < edgeFormat1.getPointList().size() && haveSameLayout; i++) {
                    haveSameLayout = isAroundPoint(edgeFormat1.getPointList().get(i), edgeFormat2.getPointList().get(i), edgeConfiguration.getDistanceAroundPointsOfEdgeBendpointsList());
                }
            }

            if (!haveSameLayout) {
                result = new FormatDifferenceImpl<EdgeFormatData>(edgeFormat1, edgeFormat2, configuration);
            }
        } else {
            result = abstractDataHaveSameLayout;
        }

        return result;
    }

    private static String toString(EObject eobject) {
        return FormatHelperImpl.toString("\n", eobject); //$NON-NLS-1$
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
                    sb.append(FormatHelperImpl.toString("\n     ", (EObject) obj)); //$NON-NLS-1$
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
                            sb.append(FormatHelperImpl.toString((EObject) object));
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
