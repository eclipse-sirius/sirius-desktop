/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.ide;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.parser.interpreter.ModuleDescriptor;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.sirius.common.acceleo.mtl.business.api.ResourceFinder;
import org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler;
import org.eclipse.sirius.common.acceleo.mtl.business.api.extension.DynamicJavaModuleCreator;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This import handler will try and import a dependency as a Java class
 * accessible in the workspace.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class WorkspaceJavaImportHandler extends AbstractImportHandler {
    /**
     * Returns the list of top-level public methods accessible from the given
     * compilation unit.
     * 
     * @param compilationUnit
     *            The compilation unit from which we need the method list.
     * @return The list of top-level public methods accessible from the given
     *         compilation unit.
     * @throws JavaModelException
     *             Thrown if the compilation unit does not exist.
     */
    private static List<IMethod> getPublicMethods(ICompilationUnit compilationUnit) throws JavaModelException {
        final List<IMethod> methods = Lists.newArrayList();
        final IType[] types = compilationUnit.getTypes();

        for (int i = 0; i < types.length; i++) {
            if (types[i].getElementType() == IJavaElement.TYPE) {
                for (IJavaElement child : types[i].getChildren()) {
                    if (child instanceof IMethod && Flags.isPublic(((IMethod) child).getFlags())) {
                        methods.add((IMethod) child);
                    }
                }
            }
        }

        return methods;
    }

    /**
     * This will return the list of all public method signatures that can be
     * retrieved from the given Java file.
     * 
     * @param javaFile
     *            The workspace-located java file we are to check for public
     *            methods.
     * @return The list of all public method signatures that can be retrieved
     *         from the given Java file.
     */
    private static List<String> getPublicSignaturesFrom(IFile javaFile) {
        final List<String> signatures = Lists.newArrayList();

        final IJavaElement javaElement = JavaCore.create(javaFile);
        if (javaElement instanceof ICompilationUnit) {
            final ICompilationUnit compilationUnit = (ICompilationUnit) javaElement;

            try {
                final List<IMethod> publicMethods = getPublicMethods(compilationUnit);

                for (IMethod method : publicMethods) {
                    final IType context = (IType) method.getParent();
                    final StringBuilder signature = new StringBuilder();
                    signature.append(resolveType(context, method.getReturnType())).append(' ');
                    signature.append(method.getElementName()).append('(');
                    final String[] params = method.getParameterTypes();
                    for (int i = 0; i < params.length; i++) {
                        signature.append(resolveType(context, params[i]));
                        if (i < (params.length - 1)) {
                            signature.append(',');
                        }
                    }
                    signature.append(')');
                    signatures.add(signature.toString());
                }
            } catch (JavaModelException e) {
                // Should never happen
            }
        }

        return signatures;
    }

    /**
     * Checks whether the given workspace file is a Java source file.
     * 
     * @param file
     *            The file to consider.
     * @return <code>true</code> if the given IFile can be considered a java
     *         source file, <code>false</code> otherwise.
     */
    private static boolean isJavaFile(IFile file) {
        return JavaCore.create(file) instanceof ICompilationUnit;
    }

    /**
     * Tries and resolve the given type signature in the given context.
     * <p>
     * A method's parameters are in a signature form when not compiled. The
     * <em>typeSignature</em> should be of the form "QString;". We will make
     * this signature into what is actually written in the source "String"; then
     * try to resolve that into the given context.
     * </p>
     * 
     * @param context
     *            Context in which to resolve the signature.
     * @param typeSignature
     *            Signature to resolve to an actual qualified type.
     * @return The qualified name of the resolved type, or the "source" value if
     *         we fail to resolve it.
     */
    private static String resolveType(IType context, String typeSignature) {
        final String typeErasure = Signature.toString(Signature.getTypeErasure(typeSignature));
        final String[] args = Signature.getTypeArguments(typeSignature);
        final String[] typeArguments = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            typeArguments[i] = Signature.toString(args[i]);
        }

        final StringBuilder resolved = new StringBuilder();
        resolved.append(resolveSourceType(context, typeErasure));
        if (typeArguments.length > 0) {
            resolved.append('<');
            for (int i = 0; i < typeArguments.length; i++) {
                if (i > 0) {
                    resolved.append(',');
                }
                resolved.append(resolveSourceType(context, typeArguments[i]));
            }
            resolved.append('>');
        }
        return resolved.toString();
    }

    /**
     * Tries and resolve the given type as a "source" type (i.e : not a
     * signature returned by the jdt (&quote;[LString;&quot;) but as it would be
     * written in the source code (&quot;String[]&quot;)).
     * 
     * @param context
     *            Context in which to resolve the signature.
     * @param sourceType
     *            The type we are trying to resolve.
     * @return The qualified resolved type or the "source" value if we failed to
     *         resolve it.
     */
    private static String resolveSourceType(IType context, String sourceType) {
        try {
            final String wildcard = "? extends ";  //$NON-NLS-1$
            final String[][] erasure;
            if (sourceType.contains(wildcard)) {
                erasure = context.resolveType(sourceType.substring(sourceType.indexOf(wildcard) + wildcard.length()));
            } else {
                erasure = context.resolveType(sourceType);
            }

            String resolved = null;
            if (erasure != null && erasure.length > 0) {
                // resolve to the first candidate if there are ambiguous matches
                final String[] parts = erasure[0];
                resolved = Joiner.on('.').join(parts);
            } else {
                IImportDeclaration[] imports = context.getCompilationUnit().getImports();
                for (int i = 0; i < imports.length && resolved == null; i++) {
                    IImportDeclaration importDeclaration = imports[i];
                    if (!importDeclaration.isOnDemand()) {
                        final String importName = importDeclaration.getElementName();
                        if (importName.endsWith(sourceType)) {
                            resolved = importName;
                        }
                    }
                }
            }

            return resolved;
        } catch (JavaModelException e) {
            return sourceType;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler#canImport(java.util.Set,
     *      java.util.Set, java.lang.String)
     */
    @Override
    public boolean canImport(Set<String> viewpointPlugins, Set<String> viewpointProjects, String dependency) {
        return candidateExist(dependency);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler#createImport(java.util.Set,
     *      java.util.Set, java.lang.String)
     */
    @Override
    public ModuleDescriptor createImport(Set<String> viewpointPlugins, Set<String> viewpointProjects, String dependency) {
        final List<IFile> javaCandidates = findJavaCandidates(dependency);

        ModuleDescriptor module = null;
        if (!javaCandidates.isEmpty()) {
            // Only consider the first match
            final IFile javaFile = javaCandidates.get(0);

            // This URI needs to point towards the java file's project in order
            // for Acceleo to "find" it when evaluating the invoke.
            final URI moduleURI = getPlatformResourceURI(javaFile);

            module = new WorkspaceJavaModuleDescriptor(moduleURI, dependency.replace(".", IAcceleoConstants.NAMESPACE_SEPARATOR), javaFile); //$NON-NLS-1$
        }

        return module;
    }

    /**
     * This can be used to retrieve a "platform:/resource" URI corresponding to
     * the given IFile "as if it was an emtl".
     * 
     * @param file
     *            The java file we need an "emtl" URI for.
     * @return "platform:/resource" URI corresponding to the given IFile as if
     *         it was an emtl.
     */
    private URI getPlatformResourceURI(IFile file) {
        return URI.createURI("platform:/resource" + file.getFullPath().removeFileExtension().addFileExtension(IAcceleoConstants.EMTL_FILE_EXTENSION)); //$NON-NLS-1$
    }

    /**
     * Checks whether there is at least one candidate in the workspace for the
     * given qualified name.
     * 
     * @param qualifiedName
     *            Qualified name of the Java file we are searching for in the
     *            workspace.
     * @return <code>true</code> if we found at least one potential candidate
     *         for that qualified name in the workspace, <code>false</code>
     *         otherwise.
     */
    private boolean candidateExist(String qualifiedName) {
        String javaPath = qualifiedName.replace('.', '/');
        // only consider "java" extensions
        javaPath += ".java"; //$NON-NLS-1$

        final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        final Iterable<IProject> accessibleProjects = Iterables.filter(Arrays.asList(projects), new AccessibleProjectPredicate());
        for (IProject project : accessibleProjects) {
            try {
                final ResourceFinder javaFinder = new ResourceFinder(javaPath) {
                    /**
                     * {@inheritDoc}
                     * 
                     * @see org.eclipse.sirius.common.acceleo.mtl.business.api.ResourceFinder#visit(org.eclipse.core.resources.IResource)
                     */
                    @Override
                    public boolean visit(IResource resource) throws CoreException {
                        if (path.length == 0 || getMatches().size() != 0) {
                            return false;
                        }

                        if (isCandidate(resource) && resource instanceof IFile && isJavaFile((IFile) resource)) {
                            getMatches().add(resource);
                        }

                        // Stop at the first candidate found
                        return getMatches().size() == 0;
                    }
                };
                project.accept(javaFinder);

                if (javaFinder.getMatches().size() != 0) {
                    return true;
                }
            } catch (CoreException e) {
                // ignore this, simply don't look in this project
            }
        }
        return false;
    }

    /**
     * Searches the workspace for a viewpoint project containing a Java file
     * going by the given qualified name. All potential matches will be
     * returned.
     * 
     * @param qualifiedName
     *            The qualified name of the Java file we seek.
     * @return All potential matches for the given qualified name in viewpoint
     *         projects.
     */
    private List<IFile> findJavaCandidates(String qualifiedName) {
        final List<IFile> candidates = Lists.newArrayList();

        String javaPath = qualifiedName.replace('.', '/');
        // only consider "java" extensions
        javaPath += ".java"; //$NON-NLS-1$

        final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        final Iterable<IProject> accessibleProjects = Iterables.filter(Arrays.asList(projects), new AccessibleProjectPredicate());
        for (IProject project : accessibleProjects) {
            try {
                final ResourceFinder javaFinder = new ResourceFinder(javaPath);
                project.accept(javaFinder);

                Iterables.addAll(candidates, Iterables.filter(javaFinder.getMatches(), IFile.class));
            } catch (CoreException e) {
                // ignore this, simply don't look in this project
            }
        }

        return candidates;
    }

    /**
     * This predicate will check whether the input project is accessible and
     * open.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private static final class AccessibleProjectPredicate implements Predicate<IProject> {
        /**
         * {@inheritDoc}
         * 
         * @see com.google.common.base.Predicate#apply(java.lang.Object)
         */
        @Override
        public boolean apply(IProject input) {
            return input.isAccessible() && input.isOpen() && !input.isDerived();
        }
    }

    /**
     * This implementation of a {@link ModuleDescriptor} overrides the
     * {@link ModuleDescriptor#getModuleContent()} method in order to always
     * take the latest content of its underlying java file into account.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private static final class WorkspaceJavaModuleDescriptor extends ModuleDescriptor {
        /** The underlying java file containing this module's content. */
        private final IFile javaFile;

        /** Don't re-parse the file's content if it has not changed. */
        private long lastTimeStamp;

        /**
         * We'll only parse the file once per change of its
         * {@link #lastTimeStamp time stamp}.
         */
        private String moduleContent;

        /**
         * Instantiates a module descriptor given the URI and qualified name it
         * should use.
         * <p>
         * As opposed to the default ModuleDescriptor, this does not take a
         * final "moduleContent" into account at creation time : the content
         * will be re-parsed from the given java file each time we need to
         * access it.
         * </p>
         * 
         * @param moduleURI
         *            URI of the module that should be built.
         * @param qualifiedName
         *            Qualified name of this module in case it should be later
         *            imported.
         * @param javaFile
         *            Java file (from the workspace) from where the content of
         *            this module should be parsed.
         */
        WorkspaceJavaModuleDescriptor(URI moduleURI, String qualifiedName, IFile javaFile) {
            // The "module content" for this descriptor will be dynamic.
            super(moduleURI, qualifiedName, ""); //$NON-NLS-1$
            this.javaFile = javaFile;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.acceleo.parser.interpreter.ModuleDescriptor#getModuleContent()
         */
        @Override
        public String getModuleContent() {
            final long modificationStamp = getModificationStamp(javaFile);
            if (lastTimeStamp == IResource.NULL_STAMP || lastTimeStamp == modificationStamp) {
                return moduleContent;
            }

            final List<String> publicSignatures = getPublicSignaturesFrom(javaFile);
            final String javaQualifiedName = getQualifiedName().replace(IAcceleoConstants.NAMESPACE_SEPARATOR, "."); //$NON-NLS-1$
            moduleContent = DynamicJavaModuleCreator.createModule(javaQualifiedName, publicSignatures);
            lastTimeStamp = modificationStamp;
            return moduleContent;
        }

        /**
         * Returns the latest modification time stamp of the given resource.
         * Makes use of the {@link ITextFileBuffer} APIs in order to retrieve
         * the actual modification time stamp of IFiles
         * (IFile.getModificationStamp() seems unreliable).
         * 
         * @param resource
         *            The resource for which we need the latest modification
         *            time stamp.
         * @return The latest modification time stamp of the given resource,
         *         {@link IResource#NULL_STAMP} if it is located in a closed
         *         project.
         */
        private long getModificationStamp(IResource resource) {
            if (resource instanceof IFile) {
                final ITextFileBuffer buffer = FileBuffers.getTextFileBufferManager().getTextFileBuffer(resource.getFullPath(), LocationKind.IFILE);
                if (buffer != null && buffer.getDocument() instanceof IDocumentExtension4) {
                    long stamp = ((IDocumentExtension4) buffer.getDocument()).getModificationStamp();
                    if (stamp == IDocumentExtension4.UNKNOWN_MODIFICATION_STAMP) {
                        stamp = IResource.NULL_STAMP;
                    }
                    return stamp;
                }
            }
            return resource.getModificationStamp();
        }
    }
}
