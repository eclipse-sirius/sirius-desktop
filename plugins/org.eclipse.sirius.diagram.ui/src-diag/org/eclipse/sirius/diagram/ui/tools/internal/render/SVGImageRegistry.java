/*******************************************************************************
 * Copyright (c) 2022, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.render;


import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMSVGElement;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.util.SVGConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Registers the SVG images referenced in a {@link Diagram} for later retrieval as \<symbol\> during SVG export.
 *
 * @author Nathalie Lepine
 */
public final class SVGImageRegistry {
    
    private static final String FILE_PROTOCOL = "file:"; //$NON-NLS-1$
    
    /**
     * Px unit.
     */
    private static final String PX_UNIT = "px"; //$NON-NLS-1$

    /**
     * Link symbol.
     */
    private static final String LINK_SYMBOL = "#"; //$NON-NLS-1$

    /**
     * The prefix used for new ids of inlined imagess.
     */
    // This avoid conflict with batik which uses regular names
    // and imageURL associated id which use letters and digits.
    private static final String NEW_ID_PREFIX = "_"; //$NON-NLS-1$

    /**
     * Register a UUID for each image URL.
     */
    private static final Map<String, String> UUID_REGISTRY = new HashMap<>();

    // UUID_REGISTRY cannot be sorted by key as key may contains workspace segment.
    private static final Comparator<Map.Entry<String, String>> UUID_SORTER = Comparator.comparing(Map.Entry::getValue);

    /**
     * Register a SVG Document for each image URL.
     */
    private static final Map<String, Element> SVG_DOCUMENT_REGISTRY = new HashMap<>();

    // Using counter limits the need to perform a conflict search
    private static final Map<String, Integer> GENERATED_IDS = new HashMap<>();

    
    // Create a UC name from a String.
    private static final Function<String, String> UUID_CREATE = url -> {
        String path = url;
        if (url.startsWith(FILE_PROTOCOL)) {
            // For workspace images, only canonical path is provided.
            // Unfortunately, finding platform path may lead to several values 
            // (Virtual container or sub-projects)
            // But the name of the file never changes.
            // So we can keep the name and add counter to ensure unicity.
            // The value will be unique and constant for all hosts 
            // as long as the order of elements stays the same (no modification).
            int segmentPos = url.lastIndexOf('/'); // only separator for URL.
            if (segmentPos != -1) {
                path = FILE_PROTOCOL + url.substring(segmentPos) + '_' + UUID_REGISTRY.size();
            } // else url whould be invalid, let use it.
        }
        return UUID.nameUUIDFromBytes(path.getBytes()).toString();
    };
    
    
    /**
     * Constructor.
     */
    private SVGImageRegistry() {
    }

    /**
     * Get UUID corresponding to imageURL.
     * 
     * @param imageURL
     *            String
     * @return UUID corresponding to imageURL
     */
    public static String registerUUID(String imageURL) {
        return UUID_REGISTRY.computeIfAbsent(imageURL, UUID_CREATE);
    }

    /**
     * Get SVGDocument corresponding to imageURL.
     * 
     * @param imageURL
     *            String
     * @return SVGDocument corresponding to imageURL
     */
    public static Element registerSVGDocument(String imageURL, Document document) {
        Element element = SVG_DOCUMENT_REGISTRY.get(imageURL);
        if (element == null) {
            SVG_DOCUMENT_REGISTRY.put(imageURL, document.getDocumentElement());
        }
        return element;
    }

    /**
     * Clear registry.
     */
    public static void reset() {
        GENERATED_IDS.clear();
        UUID_REGISTRY.clear();
        SVG_DOCUMENT_REGISTRY.clear();
    }

    /**
     * Get SVG image symbol tag if imageURI is valid.
     * 
     * @param imageURI
     *            String
     * @return SVG image symbol tag.
     */
    public static Optional<Element> getReferencedImage(String imageURI) {
        return Optional.ofNullable(SVG_DOCUMENT_REGISTRY.get(imageURI));
    }

    /**
     * Check if images registry is not empty.
     * 
     * @return if images registry is not empty.
     */
    public static boolean hasReferencedImages() {
        return !SVG_DOCUMENT_REGISTRY.isEmpty();
    }

    /**
     * Get all images URLs in ImageRegistry.
     * <p>
     * Order of the elements is not based on registration order but is predictable.<br />
     * (Based on UUID sorting
     * </p>
     * 
     * @return all images URLs in ImageRegistry.
     */
    public static Collection<String> getURLs() {
        return UUID_REGISTRY.entrySet().stream() // Sorting is based on entry value (uuid)
            .sorted(UUID_SORTER) //  as key may include canonical file path 
            .map(Map.Entry::getKey) // and be different for a same model.
            .collect(Collectors.toList());
    }

    /**
     * Get UUID corresponding to image URL if exists.
     * 
     * @param url
     *            String
     * @return UUID corresponding to image URL if exists.
     */
    public static Optional<String> getUUID(String url) {
        return Optional.ofNullable(UUID_REGISTRY.get(url));
    }

    /**
     * Clone document and save it SVGImageRegistry. Update width and height in viewbox. Update all ids. All ids are
     * updated because we could have 2 symbol tags with the same bundle image: they must have different id otherwise
     * elements of the second symbol tag will reference elements of the first symbol tag. Note that clone document does
     * not change ids.
     * 
     * @param doc
     *            Document
     * @param svgArea
     *            Rectangle
     * @return clone document to add in registry
     */
    public static Document getSVGDocument(Document doc, Rectangle svgArea) {
        Document copiedDocument = getSVGDocument(doc);

        // update width and height in view box
        updateViewBox(copiedDocument, svgArea);

        return copiedDocument;
    }

    /**
     * Clone document and save it SVGImageRegistry. Update all ids. All ids are updated because we could have 2 symbol
     * tags with the same bundle image: they must have different id otherwise elements of the second symbol tag will
     * reference elements of the first symbol tag. Note that clone document does not change ids.
     * 
     * @param doc
     *            Document
     * @param svgArea
     *            Rectangle
     * @return clone document to add in registry
     */
    public static Document getSVGDocument(Document doc) {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();

        // create a new document with doc clone nodes
        Document copiedDocument = DOMUtilities.deepCloneDocument(doc, impl);

        // update all ids and their references in the document.
        Map<String, String> ids = new TreeMap<>(Comparator.reverseOrder());
        updateIDs(copiedDocument.getDocumentElement(), ids);
        updateLinks(copiedDocument.getDocumentElement(), ids);

        return copiedDocument;
    }

    /**
     * Update width and height in view box.
     * 
     * @param document
     *            Document
     * @param svgArea
     *            Rectangle
     */
    private static void updateViewBox(Document document, Rectangle svgArea) {
        for (int i = 0; i < document.getChildNodes().getLength(); i++) {
            org.w3c.dom.Node node = document.getChildNodes().item(i);
            if (node instanceof SVGOMSVGElement) {
                String viewBoxValue = ((Element) node).getAttributeNS(null, SVGConstants.SVG_VIEW_BOX_ATTRIBUTE);
                if (!StringUtil.isEmpty(viewBoxValue)) {
                    updateSize((Element) node, svgArea);
                }
            }

        }
    }

    /**
     * Update width and height in element.
     * 
     * @param document
     *            Document
     * @param svgArea
     *            Rectangle
     */
    private static void updateSize(Element element, Rectangle svgArea) {
        element.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(svgArea.width) + PX_UNIT);
        element.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(svgArea.height) + PX_UNIT);
    }

    /**
     * Update all links in node hierarchy containing ids(k) with ids(k->v).
     * 
     * @param node
     *            Node
     * @param ids
     *            Map<String, String>
     */
    private static void updateLinks(Node node, Map<String, String> ids) {
        updateLink(node, ids);
        for (Node c = node.getFirstChild(); c != null; c = c.getNextSibling()) {
            updateLinks(c, ids);
        }
    }

    /**
     * Update all links in node attributes containing ids(k) with ids(k->v).
     * 
     * @param node
     *            Node
     * @param ids
     *            Map<String, String>
     */
    private static void updateLink(Node node, Map<String, String> ids) {
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node item = node.getAttributes().item(i);
                String nodeValue = item.getNodeValue();
                if (!SVGConstants.SVG_ID_ATTRIBUTE.equals(item.getNodeName()) && nodeValue != null && !nodeValue.isEmpty()) {
                    ids.forEach((k, v) -> {
                        String currentNodeValue = item.getNodeValue();
                        if (currentNodeValue.contains(LINK_SYMBOL + k)) {
                            String newId = ids.get(k);
                            /*
                             * NEW_ID_PREFIX of newId on ensures that a given id is replaced only once. Otherwise if two
                             * ids begin by the same characters they can either be replaced twice or replaced by the non
                             * corresponding newID.
                             */
                            /*
                             * ids is a TreeMap with reverse ordering that ensure that longer ids are replaced before
                             * shorter For example if there are two ids, id1 and id11, we must not replace id1 character
                             * of the id11 id by newid1. That's why id11 must be managed first so that #id11 will be
                             * replaced by #_newId11
                             */
                            String newValue = currentNodeValue.replaceAll(LINK_SYMBOL + k, LINK_SYMBOL + newId);
                            item.setNodeValue(newValue);
                        }
                    });

                }
            }
        }
    }

    /**
     * Update all ids in node hierarchy.
     * 
     * @param node
     *            Node
     * @param ids
     *            Map<String, String>
     */
    private static void updateIDs(Node node, Map<String, String> ids) {
        updateId(node, ids);
        for (Node c = node.getFirstChild(); c != null; c = c.getNextSibling()) {
            updateIDs(c, ids);
        }
    }

    private static int getGeneratedSuffixId(String base) {
        Integer result = GENERATED_IDS.getOrDefault(base, 0);
        GENERATED_IDS.put(base, result + 1);
        return result;
    }

    /**
     * Update all ids in node attributes.
     * 
     * @param node
     *            Node
     * @param ids
     *            Map<String, String>
     */
    private static void updateId(Node node, Map<String, String> ids) {
        if (node.hasAttributes()) {
            NamedNodeMap attr = node.getAttributes();
            Node idItem = attr.getNamedItem(SVGConstants.SVG_ID_ATTRIBUTE);
            if (idItem != null) {
                String idValue = idItem.getNodeValue();
                int suffix = getGeneratedSuffixId(idValue);
                String newValue = NEW_ID_PREFIX + idValue + '_' + suffix;
                ids.put(idValue, newValue);
                idItem.setNodeValue(newValue);
            }
        }
    }
}
