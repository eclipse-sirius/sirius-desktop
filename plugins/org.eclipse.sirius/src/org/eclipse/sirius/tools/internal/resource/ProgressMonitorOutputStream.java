/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.resource;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Updates a progress monitor as bytes are write to the output stream.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ProgressMonitorOutputStream extends FilterOutputStream {

    private IProgressMonitor monitor;

    private int updateIncrement;

    private long bytesWritten;

    private long lastUpdate = -1;

    private long nextUpdate;

    private long previousWritten;

    /**
     * Creates a progress monitoring output stream.
     * 
     * @param out
     *            the underlying output stream
     * @param bytesTotal
     *            the number of bytes to write in total (passed to
     *            updateMonitor())
     * @param updateIncrement
     *            the number of bytes written between updates
     * @param monitor
     *            the progress monitor
     */
    public ProgressMonitorOutputStream(OutputStream out, long bytesTotal, int updateIncrement, IProgressMonitor monitor) {
        super(out);
        this.updateIncrement = updateIncrement;
        this.monitor = monitor;
        update(true);
    }

    private void updateMonitor(long writtenBytes) {
        long progress = writtenBytes - previousWritten;
        while (progress > Integer.MAX_VALUE) {
            monitor.worked(Integer.MAX_VALUE);
            progress -= Integer.MAX_VALUE;
        }
        monitor.worked((int) progress);
        previousWritten = writtenBytes;
    }

    /**
     * Wraps the underlying stream's method. Updates the progress monitor to the
     * final number of bytes written.
     * 
     * @throws IOException
     *             if an i/o error occurs
     */
    public void close() throws IOException {
        try {
            out.close();
        } finally {
            update(true);
        }
    }

    /**
     * Wraps the underlying stream's method. Updates the progress monitor if the
     * next update increment has been reached.
     * 
     * @param b
     *            the data to write
     * @throws IOException
     *             if an i/o error occurs
     */
    @Override
    public void write(int b) throws IOException {
        out.write(b);
        bytesWritten += 1;
        update(false);
    }

    /**
     * Wraps the underlying stream's method. Updates the progress monitor if the
     * next update increment has been reached.
     * 
     * @param buffer
     *            the data to write
     * @param offset
     *            where to write it
     * @param length
     *            the length of the buffer to write
     * @throws IOException
     *             if an i/o error occurs
     */
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        try {
            out.write(buffer, offset, length);
            bytesWritten += length;
            update(false);
        } catch (InterruptedIOException e) {
            bytesWritten += e.bytesTransferred;
            update(false);
            throw e;
        }
    }

    private void update(boolean now) {
        if (bytesWritten >= nextUpdate || now) {
            nextUpdate = bytesWritten - (bytesWritten % updateIncrement);
            if (nextUpdate != lastUpdate)
                updateMonitor(nextUpdate);
            lastUpdate = nextUpdate;
            nextUpdate += updateIncrement;
        }
    }
}
// CHECKSTYLE:ON
