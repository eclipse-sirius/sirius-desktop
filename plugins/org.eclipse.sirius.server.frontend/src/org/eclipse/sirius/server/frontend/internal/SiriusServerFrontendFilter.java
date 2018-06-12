/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.frontend.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Servlet filter used to redirect the request to provide a single page
 * application.
 *
 * @author sbegaudeau
 */
public class SiriusServerFrontendFilter implements Filter {

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
            if (this.isImage(requestURI)) {
                this.handleImage(httpServletRequest, httpServletResponse, chain);
            } else if (this.isFrontEnd(requestURI)) {
                this.redirectToFrontEnd(httpServletRequest, httpServletResponse);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    /**
     * Indicates if the given request URI matches an image path.
     *
     * @param requestURI
     *            The URI of the request
     * @return <code>true</code> if the given request URI matches an image path.
     */
    private boolean isImage(String requestURI) {
        boolean isImagePath = requestURI.startsWith("/images"); //$NON-NLS-1$

        boolean isImageExtension = requestURI.endsWith(JPG);
        isImageExtension = isImageExtension || requestURI.endsWith(JPEG);
        isImageExtension = isImageExtension || requestURI.endsWith(GIF);
        isImageExtension = isImageExtension || requestURI.endsWith(BMP);
        isImageExtension = isImageExtension || requestURI.endsWith(PNG);
        isImageExtension = isImageExtension || requestURI.endsWith(SVG);

        return isImagePath && isImageExtension;
    }

    /**
     * Indicates if the given request URI should be redirected to the front end
     * for a proper single page application.
     *
     * @param requestURI
     *            The URI of the request
     * @return <code>true</code> if the given request should be redirected to
     *         the front end
     */
    private boolean isFrontEnd(String requestURI) {
        boolean isFrontEnd = requestURI.startsWith("/projects"); //$NON-NLS-1$
        return isFrontEnd;
    }

    /**
     * Returns the image matching the given request.
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
    private void handleImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();
        String[] segments = requestURI.split("/"); //$NON-NLS-1$
        if (segments.length >= 4) {
            String bundleSymbolicName = segments[2];
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
     * @return The size of the content transfered from the input stream to the
     *         output stream
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
     * Redirects the request to the front end in order to have a proper single
     * page application.
     *
     * @param httpServletRequest
     *            The request
     * @param httpServletResponse
     *            The response
     * @throws ServletException
     *             In case of error
     * @throws IOException
     *             In case of error
     */
    private void redirectToFrontEnd(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, httpServletResponse); //$NON-NLS-1$
    }

    @Override
    public void destroy() {
        // Do nothing
    }

}
