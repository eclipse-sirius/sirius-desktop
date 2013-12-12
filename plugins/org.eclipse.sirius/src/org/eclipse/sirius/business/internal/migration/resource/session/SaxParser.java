/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.resource.session;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.internal.migration.resource.MigrationUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.common.collect.Maps;

/**
 * A sax handler for migration preprocessing.
 * 
 * @author ymortier
 */
public class SaxParser extends DefaultHandler {

    private static final String DOT = ".";

    private static final String ORI_STYLE = "originalStyle";

    /** The double quote string. */
    private static final String QUOTE = "\"";

    /** Slash string. */
    private static final String SLASH = "/";

    /** The open tag string. */
    private static final String OPEN_TAG = "<";

    /** The close tag String. */
    private static final String CLOSE_TAG = ">";

    /** The begining of a tag that closes a block. */
    private static final String OPEN_CLOSE_BLOCK = "</";

    /** The end of a tag that close a block. */
    // NOT USE private static final String CLOSE_INLINE_TAG = "/>";
    /** The string that opens the declaration of the value of an attribute. */
    private static final String OPEN_ATTRIBUTE_DECL = QUOTE;

    /** The string that closes the declaration of the value of an attribute. */
    private static final String CLOSE_ATTRIBUTE_DECL = QUOTE;

    /** Attribute declaration. */
    private static final String ATTRIBUTE_DECL = "=";

    /** The uri delimiter. */
    private static final String URI_DELIMITER = ":";

    /** The Line separator to use. */
    private static final String SYS_LS = "\n"; // System.getProperty(

    // "line.separator");

    boolean originalStyle;

    /** The map of strings to replace in attribute (old -> new). */
    private final Map<String, String> old2NewAttributes = Maps.newLinkedHashMap();

    {
        old2NewAttributes.put(Pattern.quote("&"), "&amp;");
        old2NewAttributes.put(Pattern.quote("<"), "&lt;");
        old2NewAttributes.put(Pattern.quote(QUOTE), "&quot;");
        old2NewAttributes.put(Pattern.quote(DOT + MigrationUtil.MODELER_DESCRIPTION_FILE_EXTENSION_V3 + "#//"), ".odesign#//");
        old2NewAttributes.put(Pattern.quote("/@visualAspect/"), SLASH);
    }

    /** Maps URI and their prefixes. */
    private final Map<String, String> uriToPrefixes = new HashMap<String, String>();

    /** The stack of tags. */
    private final Stack<String> stack = new Stack<String>();

    /** The output. */
    private OutputStream output;

    /** The errors. */
    private final List<SAXParseException> errors = new LinkedList<SAXParseException>();

    /** The fatal errors. */
    private final List<SAXParseException> fatalErrors = new LinkedList<SAXParseException>();

    /** The warnings. */
    private final List<SAXParseException> warnings = new LinkedList<SAXParseException>();

    /** This map will allow us to hold */
    private final Map<String, String> representationToVP = new HashMap<String, String>();

    /** True if the tag ownedViews is already added. */
    private boolean isOwnedViewsAdded;

    /** True if the tag ownedViews is closed. */
    private boolean isOwnedViewsClosed;

    /** The monitor. */
    private IProgressMonitor monitor;

    /** The XML source. */
    private InputSource source;

    /**
     * This will hold a reference to all description files providing viewpoints
     * according to
     * {@link org.eclipse.sirius.business.api.componentization.ViewpointRegistry#getViewpoints()}
     * .
     */
    private final Set<Resource> descriptionFiles;

    /**
     * Cosntructor.
     * 
     * @param resources
     *            : all the description files
     */
    public SaxParser(final Set<Resource> resources) {
        this.descriptionFiles = resources;
    }

    /**
     * Preprocess the specified diagram file.
     * 
     * @param aird
     *            the diagram file.
     * @param o
     *            the output file.
     * @param m
     *            the monitor
     */
    public void preProcess(final IFile aird, final File o, final IProgressMonitor m) {
        xmlTransform(aird, o, m);
        reportErrors();
    }

    private void xmlTransform(final IFile aird, final File o, final IProgressMonitor m) {
        this.monitor = m;
        InputStream input = null;
        try {
            final XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(this);
            reader.setErrorHandler(this);
            this.output = new BufferedOutputStream(new FileOutputStream(o));
            input = new BufferedInputStream(new FileInputStream(new File(aird.getRawLocation().toOSString())));
            source = new InputSource(input);
            this.append("<?xml version=\"1.0\" encoding=\"");
            this.append("UTF-8");
            this.append("\"?>");
            this.append("\n");
            reader.parse(source);
        } catch (final SAXException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final FileNotFoundException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    SiriusPlugin.getDefault().error(e.getMessage(), e);
                }
            }
            if (this.output != null) {
                try {
                    this.output.close();
                } catch (final IOException e) {
                    SiriusPlugin.getDefault().error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(final String uri, final String localName, final String name, final Attributes attributes) throws SAXException {
        if (localName.equals(ORI_STYLE)) {
            originalStyle = true;
            return;
        }

        if (this.stack.size() == 1 && !isOwnedViewsAdded) {
            //
            // Adds the new root, DAnalysis.
            append(OPEN_TAG);
            append("viewpoint:DAnalysis");
            append(CLOSE_TAG);
            append(SYS_LS);
        }

        // The tag
        append(OPEN_TAG);
        if (this.stack.size() != 1 || isOwnedViewsAdded) {
            if (uri != null && !StringUtil.isEmpty(uri) && this.uriToPrefixes.get(uri) != null) {
                append(this.uriToPrefixes.get(uri));
                append(URI_DELIMITER);
            }
            if ("originMapping".equals(localName)) {
                append("actualMapping");
            } else {
                append(localName);
            }
        } else if (this.stack.size() == 1 && !isOwnedViewsAdded) {
            //
            // Transforms the tag to ownedViews.
            isOwnedViewsAdded = true;
            append("ownedViews xmi:type=\"viewpoint:DRepresentationContainer\"");
        }

        if (this.stack.isEmpty()) {
            //
            // Prints URI mappings.
            for (final Entry<String, String> entry : this.uriToPrefixes.entrySet()) {
                printPrefixMapping(entry.getValue(), entry.getKey());
            }
        }

        // The attributes
        printAttributes(attributes);
        append(CLOSE_TAG);

        this.stack.push(name);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(final String uri, final String localName, final String name) throws SAXException {
        if (localName.equals(ORI_STYLE)) {
            originalStyle = false;
            return;
        }
        if (this.stack.size() == 2 && !isOwnedViewsClosed) {
            isOwnedViewsClosed = true;
            //
            // Adds the new root, DAnalysis.
            append("</ownedViews>");
            append(SYS_LS);
            append(OPEN_CLOSE_BLOCK);
            append("viewpoint:DAnalysis");
            append(CLOSE_TAG);
        } else {
            assert !this.stack.isEmpty();
            append(OPEN_CLOSE_BLOCK);
            if (uri != null && !StringUtil.isEmpty(uri) && this.uriToPrefixes.get(uri) != null) {
                append(this.uriToPrefixes.get(uri));
                append(URI_DELIMITER);
            }
            if ("originMapping".equals(localName)) {
                append("actualMapping");
            } else {
                append(localName);
            }
            append(CLOSE_TAG);
        }
        this.stack.pop();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        append(new String(ch, start, length));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startPrefixMapping(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.uriToPrefixes.put(uri, prefix);
    }

    private void printPrefixMapping(final String prefix, final String uri) {
        append(" ");
        append("xmlns");
        append(URI_DELIMITER);
        append(prefix);
        append(ATTRIBUTE_DECL);
        append(OPEN_ATTRIBUTE_DECL);
        append(uri);
        append(CLOSE_ATTRIBUTE_DECL);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#processingInstruction(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void processingInstruction(final String target, final String data) throws SAXException {
        super.processingInstruction(target, data);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endPrefixMapping(java.lang.String)
     */
    @Override
    public void endPrefixMapping(final String prefix) throws SAXException {
        final Set<String> urisToRemove = new HashSet<String>();
        for (final Entry<String, String> entry : this.uriToPrefixes.entrySet()) {
            if (entry.getValue().equals(prefix)) {
                urisToRemove.add(entry.getKey());
            }
        }
        for (final String uri : urisToRemove) {
            this.uriToPrefixes.remove(uri);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
     */
    @Override
    public void error(final SAXParseException e) throws SAXException {
        this.errors.add(e);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
     */
    @Override
    public void fatalError(final SAXParseException e) throws SAXException {
        this.fatalErrors.add(e);
    }

    /**
     * 
     */
    private void reportErrors() {
        for (final SAXParseException fatalError : fatalErrors) {
            SiriusPlugin.getDefault().error(fatalError.getMessage(), fatalError);
        }
        for (final SAXParseException error : errors) {
            SiriusPlugin.getDefault().error(error.getMessage(), error);
        }
        for (final SAXParseException warning : warnings) {
            SiriusPlugin.getDefault().warning(warning.getMessage(), warning);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
     */
    @Override
    public void warning(final SAXParseException e) throws SAXException {
        this.warnings.add(e);
    }

    private void printAttributes(final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            if (!attributes.getLocalName(i).equals(ORI_STYLE) && !originalStyle) {
                append(" ");
                if (attributes.getURI(i) != null && !StringUtil.isEmpty(attributes.getURI(i)) && this.uriToPrefixes.get(attributes.getURI(i)) != null) {
                    append(this.uriToPrefixes.get(attributes.getURI(i)));
                    append(URI_DELIMITER);
                }
                append(attributes.getLocalName(i));
                append(ATTRIBUTE_DECL);
                append(OPEN_ATTRIBUTE_DECL);
                append(transformAttributeValue(attributes.getQName(i), attributes.getValue(i)));
                append(CLOSE_ATTRIBUTE_DECL);
            }
        }
    }

    private String transformAttributeValue(final String attributeQName, final String value) {
        String result = value;
        if (value.contains("ownedViewpointsDescriptions")) {
            final String vp = findSiriusName(value);
            result = value.replaceAll("\\Q/@ownedViewpointsDescriptions[\\E", "/@ownedViewpoints[name='" + vp + "']/@ownedRepresentations[");
        }
        for (Entry<String, String> replacement : old2NewAttributes.entrySet()) {
            result = result.replaceAll(replacement.getKey(), replacement.getValue());
        }
        return result;
    }

    private String findSiriusName(final String value) {
        String descriptionName = new String(value.substring(0, value.indexOf("#"))); //$NON-NLS-1$
        descriptionName = descriptionName.substring(descriptionName.lastIndexOf(SLASH) + 1);
        if (descriptionName.endsWith(DOT + MigrationUtil.MODELER_DESCRIPTION_FILE_EXTENSION_V3)) {
            descriptionName = descriptionName.replace(DOT + MigrationUtil.MODELER_DESCRIPTION_FILE_EXTENSION_V3, DOT + SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
        }

        final int startRepresentationName = value.indexOf("=", value.indexOf("ownedViewpointsDescriptions"));
        final int endRepresentationName = value.indexOf("]", startRepresentationName);
        final String representationName = URI.decode(new String(value.substring(startRepresentationName + 2, endRepresentationName - 1)));

        if (representationToVP.containsKey(representationName)) {
            return representationToVP.get(representationName);
        }

        for (final Resource candidate : descriptionFiles) {
            if (candidate.getURI().lastSegment().equals(descriptionName)) {
                for (final EObject candidateRoot : candidate.getContents()) {
                    if (candidateRoot instanceof Group) {
                        for (final Viewpoint candidateSirius : ((Group) candidateRoot).getOwnedViewpoints()) {
                            for (final RepresentationDescription desc : candidateSirius.getOwnedRepresentations()) {
                                if (representationName.equals(desc.getName())) {
                                    representationToVP.put(representationName, candidateSirius.getName());
                                    return URI.encodeSegment(candidateSirius.getName(), false);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Couldn't find viewpoint name for representation " + representationName);
    }

    private void append(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            this.output.write(bytes);
            this.monitor.worked(bytes.length / 5);
        } catch (final UnsupportedEncodingException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        }
    }
}
