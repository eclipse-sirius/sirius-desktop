/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.server.images.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.server.images.api.ISiriusServerImagesConstants;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.osgi.framework.Bundle;

/**
 * Servlet filter used to provide support for images.
 *
 * @author sbegaudeau
 */
public class SiriusServerImagesFilter implements Filter {

    /** The extension of jpg files. */
    private static final String JPG = "jpg"; //$NON-NLS-1$

    /** The extension of jpeg files. */
    private static final String JPEG = "jpeg"; //$NON-NLS-1$

    /** The jpeg mime type. */
    private static final String JPEG_MIME_TYPE = "image/jpeg"; //$NON-NLS-1$

    /** The extension of giffiles. */
    private static final String GIF = "gif"; //$NON-NLS-1$

    /** The gif mime type. */
    private static final String GIF_MIME_TYPE = "image/gif"; //$NON-NLS-1$

    /** The extension of bmp files. */
    private static final String BMP = "bmp"; //$NON-NLS-1$

    /** The bmp mime type. */
    private static final String BMP_MIME_TYPE = "image/bmp"; //$NON-NLS-1$

    /** The extension of pngfiles. */
    private static final String PNG = "png"; //$NON-NLS-1$

    /** The png mime type. */
    private static final String PNG_MIME_TYPE = "image/png"; //$NON-NLS-1$

    /** The extension of svg files. */
    private static final String SVG = "svg"; //$NON-NLS-1$

    /** The svg mime type. */
    private static final String SVG_MIME_TYPE = "image/svg+xml"; //$NON-NLS-1$

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String requestURI = httpServletRequest.getRequestURI();
            if (this.isStaticImage(requestURI)) {
                this.handleStaticImage(httpServletRequest, httpServletResponse, chain);
            } else if (this.isEObjectImage(httpServletRequest)) {
                this.handleEObjectImage(httpServletRequest, httpServletResponse, chain);
            }
        }
    }

    /**
     * Indicates if the given request URI matches a static image path.
     *
     * @param requestURI
     *            The URI of the request
     * @return <code>true</code> if the given request URI matches an image path.
     */
    private boolean isStaticImage(String requestURI) {
        boolean isImagePath = requestURI.startsWith(ISiriusServerImagesConstants.IMAGES_PATH);

        boolean isImageExtension = requestURI.endsWith(JPG);
        isImageExtension = isImageExtension || requestURI.endsWith(JPEG);
        isImageExtension = isImageExtension || requestURI.endsWith(GIF);
        isImageExtension = isImageExtension || requestURI.endsWith(BMP);
        isImageExtension = isImageExtension || requestURI.endsWith(PNG);
        isImageExtension = isImageExtension || requestURI.endsWith(SVG);

        return isImagePath && isImageExtension;
    }

    /**
     * Returns the static image matching the given request.
     *
     * @param httpServletRequest
     *            The request
     * @param httpServletResponse
     *            The response
     * @param chain
     *            The filter chain
     * @throws ServletException
     *             In case of error
     * @throws IOException
     *             In case of error
     */
    private void handleStaticImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws ServletException, IOException {
        // requestURI = /images/projectname/path/to/a/folder/image.png
        String requestURI = httpServletRequest.getRequestURI();

        // segments = ["", "images", "projectname", "path", "to", "a", "folder", "image.png"]
        String[] segments = requestURI.split("/"); //$NON-NLS-1$
        if (segments.length >= 4) {
            String bundleSymbolicName = segments[2];

            // imagePath = path/to/a/folder/image.png
            String imagePath = Arrays.stream(segments).skip(3).reduce("", (string1, string2) -> { //$NON-NLS-1$
                return string1 + '/' + string2;
            });

            Optional<Bundle> optionalBundle = Optional.ofNullable(Platform.getBundle(bundleSymbolicName));
            Optional<URL> optionalImageUrl = optionalBundle.map(bundle -> bundle.getEntry(imagePath));
            if (optionalImageUrl.isPresent()) {
                URL url = optionalImageUrl.get();
                URLConnection connection = url.openConnection();

                httpServletResponse.setContentLength(connection.getContentLength());
                httpServletResponse.setContentType(this.getContentType(imagePath));
                try (InputStream inputStream = connection.getInputStream(); OutputStream outputStream = httpServletResponse.getOutputStream();) {
                    this.copy(inputStream, outputStream);
                    outputStream.flush();
                }
            } else {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    /**
     * Returns the content type of the image at the given path.
     *
     * @param path
     *            The path of the image
     * @return The content type of the image
     */
    private String getContentType(String path) {
        String contentType = null;

        if (path.endsWith(JPG) || path.endsWith(JPEG)) {
            contentType = JPEG_MIME_TYPE;
        } else if (path.endsWith(GIF)) {
            contentType = GIF_MIME_TYPE;
        } else if (path.endsWith(BMP)) {
            contentType = BMP_MIME_TYPE;
        } else if (path.endsWith(PNG)) {
            contentType = PNG_MIME_TYPE;
        } else if (path.endsWith(SVG)) {
            contentType = SVG_MIME_TYPE;
        }

        return contentType;
    }

    /**
     * Copy the content of the given input stream to the given output stream.
     *
     * @param inputStream
     *            The input stream
     * @param outputStream
     *            The output stream
     * @return The size of the content transfered from the input stream to the output stream
     * @throws IOException
     *             In case of error
     */
    private long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long byteRead = 0L;
        byte[] buffer = new byte[4096];
        int index;
        while ((index = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, index);
            byteRead += index;
        }
        return byteRead;
    }

    /**
     * Indicates if the given request potentially matches the path of an EObject's image.
     * 
     * @param httpServletRequest
     *            The HTTP request
     * @return <code>true</code> if the request matches an EObject's image path, <code>false</code> otherwise
     */
    private boolean isEObjectImage(HttpServletRequest httpServletRequest) {
        boolean isImagePath = httpServletRequest.getRequestURI().startsWith(ISiriusServerImagesConstants.IMAGES_PATH);
        boolean hasFragment = httpServletRequest.getParameterMap().containsKey(ISiriusServerImagesConstants.FRAGMENT);

        return isImagePath && hasFragment;
    }

    /**
     * Handles the retrieval of the image of the EObject matching the given request.
     * 
     * @param httpServletRequest
     *            The request
     * @param httpServletResponse
     *            The response
     * @param chain
     *            The filter chain
     * @throws ServletException
     *             In case of error
     * @throws IOException
     *             In case of error
     */
    private void handleEObjectImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws ServletException, IOException {
        // requestURI = /images/projectname/path/to/a/folder/resource
        String requestURI = httpServletRequest.getRequestURI();

        // segments = ["", "images", "projectname", "path", "to", "a", "folder", "resource"]
        String[] segments = requestURI.split("/"); //$NON-NLS-1$
        if (segments.length >= 4) {
            String projectName = segments[2];

            // resourcePath = path/to/a/folder/resource
            String resourcePath = Arrays.stream(segments).skip(3).reduce("", (string1, string2) -> { //$NON-NLS-1$
                return string1 + '/' + string2;
            });

            String eObjectFragment = httpServletRequest.getParameter(ISiriusServerImagesConstants.FRAGMENT);

            Optional<IProject> optionalProject = Optional.ofNullable(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
            Optional<IFile> optionalFile = optionalProject.map(iProject -> iProject.getFile(new Path(resourcePath)));
            Optional<Session> optionalSession = optionalProject.flatMap(SiriusServicesCommonOptionalUtils::toSession);
            Optional<Resource> optionalResource = optionalFile.flatMap(iFile -> {
                return optionalSession.flatMap(session -> SiriusServicesCommonOptionalUtils.toResource(session, iFile));
            });

            Optional<EObject> optionalEObject = optionalResource.map(resource -> resource.getEObject(eObjectFragment));
            Optional<Object> optionalImage = optionalEObject.flatMap(this::toImage);
            Optional<URL> optionalURL = optionalImage.flatMap(this::toURL);
            if (optionalURL.isPresent()) {
                URL imageURL = optionalURL.get();
                URLConnection connection = imageURL.openConnection();

                httpServletResponse.setContentLength(connection.getContentLength());
                httpServletResponse.setContentType(this.getContentType(imageURL.toString()));
                try (InputStream inputStream = connection.getInputStream(); OutputStream outputStream = httpServletResponse.getOutputStream();) {
                    this.copy(inputStream, outputStream);
                    outputStream.flush();
                }
            } else {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    /**
     * Converts the given image object to an URL.
     * 
     * @param object
     *            The image object
     * @return An optional containing an URL or an empty optional
     */
    private Optional<URL> toURL(Object object) {
        // @formatter:off
        Optional<URL> optionalURL = Optional.of(object)
                .filter(URL.class::isInstance)
                .map(URL.class::cast);
        
        if (!optionalURL.isPresent()) {
            optionalURL = Optional.of(object)
                    .filter(ComposedImage.class::isInstance)
                    .map(ComposedImage.class::cast)
                    .map(ComposedImage::getImages)
                    .map(Collection::stream)
                    .flatMap(Stream::findFirst)
                    .filter(URL.class::isInstance)
                    .map(URL.class::cast);
        }
        // @formatter:on
        return optionalURL;
    }

    /**
     * Retrieves the image for the given eObject or an empty optional if none could be found.
     * 
     * @param eObject
     *            The eObject
     * @return An optional with the image for the given EObject
     */
    private Optional<Object> toImage(EObject eObject) {
        // @formatter:off
        ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        composedAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        
        return Optional.of(composedAdapterFactory.adapt(eObject, IItemLabelProvider.class))
                .filter(IItemLabelProvider.class::isInstance)
                .map(IItemLabelProvider.class::cast)
                .map(labelProvider -> labelProvider.getImage(eObject));
        // @formatter:on
    }

    @Override
    public void destroy() {
        // Do nothing
    }

}
