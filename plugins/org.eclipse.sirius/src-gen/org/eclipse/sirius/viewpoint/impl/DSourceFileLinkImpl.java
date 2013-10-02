/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.DSourceFileLink;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Source File Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl#getFilePath
 * <em>File Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl#getStartPosition
 * <em>Start Position</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DSourceFileLinkImpl#getEndPosition
 * <em>End Position</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DSourceFileLinkImpl extends DNavigationLinkImpl implements DSourceFileLink {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * The default value of the '{@link #getFilePath() <em>File Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFilePath()
     * @generated
     * @ordered
     */
    protected static final String FILE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilePath() <em>File Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFilePath()
     * @generated
     * @ordered
     */
    protected String filePath = FILE_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getStartPosition()
     * <em>Start Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getStartPosition()
     * @generated
     * @ordered
     */
    protected static final int START_POSITION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getStartPosition()
     * <em>Start Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getStartPosition()
     * @generated
     * @ordered
     */
    protected int startPosition = START_POSITION_EDEFAULT;

    /**
     * The default value of the '{@link #getEndPosition() <em>End Position</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEndPosition()
     * @generated
     * @ordered
     */
    protected static final int END_POSITION_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getEndPosition() <em>End Position</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEndPosition()
     * @generated
     * @ordered
     */
    protected int endPosition = END_POSITION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DSourceFileLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DSOURCE_FILE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFilePath(String newFilePath) {
        String oldFilePath = filePath;
        filePath = newFilePath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DSOURCE_FILE_LINK__FILE_PATH, oldFilePath, filePath));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStartPosition(int newStartPosition) {
        int oldStartPosition = startPosition;
        startPosition = newStartPosition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DSOURCE_FILE_LINK__START_POSITION, oldStartPosition, startPosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getEndPosition() {
        return endPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEndPosition(int newEndPosition) {
        int oldEndPosition = endPosition;
        endPosition = newEndPosition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DSOURCE_FILE_LINK__END_POSITION, oldEndPosition, endPosition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.DSOURCE_FILE_LINK__FILE_PATH:
            return getFilePath();
        case ViewpointPackage.DSOURCE_FILE_LINK__START_POSITION:
            return getStartPosition();
        case ViewpointPackage.DSOURCE_FILE_LINK__END_POSITION:
            return getEndPosition();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ViewpointPackage.DSOURCE_FILE_LINK__FILE_PATH:
            setFilePath((String) newValue);
            return;
        case ViewpointPackage.DSOURCE_FILE_LINK__START_POSITION:
            setStartPosition((Integer) newValue);
            return;
        case ViewpointPackage.DSOURCE_FILE_LINK__END_POSITION:
            setEndPosition((Integer) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case ViewpointPackage.DSOURCE_FILE_LINK__FILE_PATH:
            setFilePath(FILE_PATH_EDEFAULT);
            return;
        case ViewpointPackage.DSOURCE_FILE_LINK__START_POSITION:
            setStartPosition(START_POSITION_EDEFAULT);
            return;
        case ViewpointPackage.DSOURCE_FILE_LINK__END_POSITION:
            setEndPosition(END_POSITION_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case ViewpointPackage.DSOURCE_FILE_LINK__FILE_PATH:
            return FILE_PATH_EDEFAULT == null ? filePath != null : !FILE_PATH_EDEFAULT.equals(filePath);
        case ViewpointPackage.DSOURCE_FILE_LINK__START_POSITION:
            return startPosition != START_POSITION_EDEFAULT;
        case ViewpointPackage.DSOURCE_FILE_LINK__END_POSITION:
            return endPosition != END_POSITION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (filePath: ");
        result.append(filePath);
        result.append(", startPosition: ");
        result.append(startPosition);
        result.append(", endPosition: ");
        result.append(endPosition);
        result.append(')');
        return result.toString();
    }

    public boolean isAvailable() {
        if (getFilePath() != null) {
            Object file = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(getFilePath()));
            if (file != null && file instanceof IFile) {
                IFile iFile = (IFile) file;
                return (iFile.isAccessible() && iFile.exists());
            }
        }
        return false;
    }
} // DSourceFileLinkImpl
