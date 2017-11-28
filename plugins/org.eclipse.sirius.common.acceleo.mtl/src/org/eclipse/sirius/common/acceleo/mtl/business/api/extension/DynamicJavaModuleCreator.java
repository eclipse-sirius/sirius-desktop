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
package org.eclipse.sirius.common.acceleo.mtl.business.api.extension;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.util.Bag;

import com.google.common.base.Joiner;

/**
 * This will be used to create an in-memory module wrapping java service
 * invocations.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class DynamicJavaModuleCreator {
    /**
     * This will hold the system specific line separator ("\n" for unix, "\r\n"
     * for dos, "\r" for mac, ...).
     */
    protected static final String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$

    /** Externalized here to avoid too many distinct uses. */
    private static final String COMMA = ","; //$NON-NLS-1$

    /** Prefix of the arguments we add to the queries. */
    private static final String ARGUMENT_PREFIX = "dynamicParameter"; //$NON-NLS-1$

    /**
     * This represents the signature of an Acceleo "invoke" call.
     * <p>
     * <ul>
     * <li>{0} represent the qualified class name of the java class from which
     * to invoke a method,</li>
     * <li>
     * {1} is the signature of the method to invoke,</li>
     * <li>
     * {2} are the arguments to pass to the invocation.</li>
     * </ul>
     * </p>
     */
    private static final String INVOKE = "invoke(''{0}'', ''{1}'', Sequence'{'{2}'}')"; //$NON-NLS-1$

    /**
     * We'll use this as the module's signature template.
     * <p>
     * <ul>
     * <li>{0} will be the module name,</li>
     * <li>{1} will be the metamodel URIs.</li>
     * </ul>
     * </p>
     */
    private static final String MODULE_SIGNATURE = "[module {0}({1})]" + LINE_SEPARATOR; //$NON-NLS-1$

    /**
     * We'll use this as the queries' signature template.
     * <p>
     * <ul>
     * <li>{0} is the query's name,</li>
     * <li>{1} are the query's parameters, the empty String as default,</li>
     * <li>{2} should be the return type of the query, OclAny as default,</li>
     * <li>{3} Should be the invoke, following the signature of {@link #INVOKE}.
     * </li>
     * </ul>
     * </p>
     * */
    private static final String QUERY_SIGNATURE = "[query public {0}({1}) : {2} = {3}/]" + LINE_SEPARATOR; //$NON-NLS-1$

    /** Does not need to be instantiated. */
    private DynamicJavaModuleCreator() {
        // Hides default constructor
    }

    /**
     * Create an in-memory wrapper for the given methods of the given java
     * class.
     * 
     * @param className
     *            The fully qualified class name of the java class which methods
     *            are to be wrapped in an in-memory module.
     * @param publicSignatures
     *            The signatures of the methods to wrap.
     * @return The created service wrapper.
     */
    public static String createModule(String className, List<String> publicSignatures) {
        final StringBuilder moduleBuffer = new StringBuilder();

        String actualClassName = trimGenericInformation(className);

        String moduleName = actualClassName.replace(".", IAcceleoConstants.NAMESPACE_SEPARATOR); //$NON-NLS-1$

        // We don't have the nsURIs yet. leave a formatting spot in the
        // signature.
        final String nsURIs = "{0}"; //$NON-NLS-1$
        moduleBuffer.append(MessageFormat.format(MODULE_SIGNATURE, moduleName, nsURIs));

        for (String signature : publicSignatures) {
            final String queryName = signature.substring(signature.indexOf(' ') + 1, signature.indexOf('('));
            final String argSignature = extractArgumentSignatureString(signature);
            final String returnType = inferOCLType(signature.substring(0, signature.indexOf(' ')));
            final String invocation = MessageFormat.format(INVOKE, actualClassName, trimGenericInformation(signature.substring(signature.indexOf(' ') + 1)), extractArgumentString(signature));

            moduleBuffer.append(MessageFormat.format(QUERY_SIGNATURE, queryName, argSignature, returnType, invocation));
        }

        return moduleBuffer.toString();
    }

/**
     * Trims all generic information (included between '<' and '>') from the given String.
     * @param className The class name we are to trim.
     * @return The trimmed class name.
     */
    private static String trimGenericInformation(String className) {
        String trimmed = className;
        int parameterIndex = trimmed.indexOf('<');
        while (parameterIndex != -1) {
            int endIndex = Math.min(trimmed.length(), trimmed.indexOf('>'));
            trimmed = trimmed.substring(0, parameterIndex) + trimmed.substring(endIndex + 1);
            parameterIndex = trimmed.indexOf('<');
        }
        return trimmed;
    }

    /**
     * Tries and extract each argument from the given signature in order to
     * create a list which OCL can understand.
     * <p>
     * For example, <code>equals(java.lang.String, java.lang.String)</code> will
     * yield <code>arg0 : String, arg1 : String</code>.
     * </p>
     * 
     * @param signature
     *            The signature from which to extract arguments.
     * @return The extracted arguments.
     */
    private static String extractArgumentSignatureString(String signature) {
        final int argStart = signature.indexOf('(') + 1;
        final int argEnd = signature.indexOf(')');

        final String argString = signature.substring(argStart, argEnd);

        String[] arguments = argString.split(COMMA);
        arguments = handleGenericArguments(arguments);

        final StringBuilder argumentsBuffer = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            final String argument = arguments[i];
            argumentsBuffer.append(ARGUMENT_PREFIX).append(i).append(':');

            argumentsBuffer.append(inferOCLType(argument));

            if (i != arguments.length - 1) {
                argumentsBuffer.append(COMMA);
            }
        }

        return argumentsBuffer.toString();
    }

    private static String[] handleGenericArguments(String[] arguments) {
        // recreate the string array for the ignored generic arguments during
        // the initial COMMA split.
        final List<String> newArguments = new ArrayList<String>();
        final String bracketLower = "<"; //$NON-NLS-1$
        final String bracketUpper = ">"; //$NON-NLS-1$
        for (int i = 0; i <= arguments.length - 1; i++) {
            if (arguments[i].contains(bracketLower) && !arguments[i].contains(bracketUpper)) {
                StringBuilder generic = new StringBuilder();
                while (!arguments[i].contains(bracketUpper) && i < arguments.length) {
                    generic.append(arguments[i]);
                    generic.append(COMMA);
                    // CHECKSTYLE:OFF
                    i++;
                    // CHECKSTYLE:ON
                }
                if (arguments[i].contains(bracketUpper)) {
                    generic.append(arguments[i]);
                }
                newArguments.add(generic.toString());
            } else {
                newArguments.add(arguments[i]);
            }
        }
        return newArguments.toArray(arguments);
    }

    /**
     * Tries and extract from the given signature the list of arguments.
     * <p>
     * For example, <code>equals(java.lang.String, java.lang.String)</code> will
     * yield <code>arg0, arg1</code>.
     * </p>
     * 
     * @param signature
     *            The signature from which to extract arguments.
     * @return The extracted arguments list.
     */
    private static String extractArgumentString(String signature) {
        final int argStart = signature.indexOf('(') + 1;
        final int argEnd = signature.indexOf(')');

        final String argString = signature.substring(argStart, argEnd);

        final String[] argumentTypes = argString.split(COMMA);
        final String[] arguments = new String[argumentTypes.length];

        for (int i = 0; i < argumentTypes.length; i++) {
            arguments[i] = ARGUMENT_PREFIX + i;
        }

        return Joiner.on(',').join(arguments);
    }

    /**
     * Returns the OCL type corresponding to the given Class.
     * 
     * @param env
     *            The ecore environment from which to seek types.
     * @param clazz
     *            The Class for which e need an OCL type.
     * @return The OCL type corresponding to the given Class.
     */
    private static String getOCLTypeName(EcoreEnvironment env, Class<?> clazz) {
        OCLStandardLibrary<EClassifier> library = env.getOCLStandardLibrary();
        EClassifier oclType = null;
        if (Number.class.isAssignableFrom(clazz)) {
            if (BigDecimal.class.isAssignableFrom(clazz) || Double.class.isAssignableFrom(clazz) || Float.class.isAssignableFrom(clazz)) {
                oclType = library.getReal();
            } else {
                oclType = library.getInteger();
            }
        } else if (String.class.isAssignableFrom(clazz)) {
            oclType = library.getString();
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            oclType = library.getBoolean();
        } else if (EObject.class.isAssignableFrom(clazz)) {
            // Can we create an instance of this EObject?
            EObject instance = null;
            try {
                instance = (EObject) clazz.newInstance();
            } catch (InstantiationException e) {
                // ignore this, instance will be null
            } catch (IllegalAccessException e) {
                // ignore this, instance will be null
            }

            if (instance != null) {
                oclType = env.getUMLReflection().asOCLType(instance.eClass());
            }
        } else if (Collection.class.isAssignableFrom(clazz)) {
            if (LinkedHashSet.class.isAssignableFrom(clazz)) {
                oclType = library.getOrderedSet();
            } else if (Set.class.isAssignableFrom(clazz)) {
                oclType = library.getSet();
            } else if (Bag.class.isAssignableFrom(clazz)) {
                oclType = library.getBag();
            } else {
                oclType = library.getSequence();
            }
        }

        final String typeName;
        if (oclType != null) {
            typeName = oclType.getName();
        } else if (EObject.class.isAssignableFrom(clazz)) {
            // This is an EObject, let us pray that it is not nested in a
            // subpackage
            final String[] parts = clazz.getName().split("\\."); //$NON-NLS-1$
            final String packageName = parts[parts.length - 2];
            final String className = parts[parts.length - 1];
            typeName = packageName + IAcceleoConstants.NAMESPACE_SEPARATOR + className;
        } else {
            typeName = library.getOclAny().getName();
        }
        return typeName;
    }

    /**
     * Tries and infer the OCL type corresponding to the given Class.
     * 
     * @param clazz
     *            The class for which we need a corresponding OCL type.
     * @return The inferred OCL type, OCLAny if we could not infer anything more
     *         sensible.
     */
    private static String inferOCLType(Class<?> clazz) {
        String oclType = "OCLAny"; //$NON-NLS-1$
        final EcoreEnvironment env = (EcoreEnvironment) new EcoreEnvironmentFactory().createEnvironment();
        if (Collection.class.isAssignableFrom(clazz)) {
            // TODO use generic types
            // EClassifier elementType = inferCollectionContentOCLType(env,
            // (Collection<?>) obj);
            final EClassifier elementType = env.getOCLStandardLibrary().getOclAny();
            CollectionKind kind = CollectionKind.SEQUENCE_LITERAL;
            if (LinkedHashSet.class.isAssignableFrom(clazz)) {
                kind = CollectionKind.ORDERED_SET_LITERAL;
            } else if (Set.class.isAssignableFrom(clazz)) {
                kind = CollectionKind.SET_LITERAL;
            } else if (Bag.class.isAssignableFrom(clazz)) {
                kind = CollectionKind.BAG_LITERAL;
            }
            oclType = env.getTypeResolver().resolveCollectionType(kind, elementType).getName();
        } else {
            oclType = getOCLTypeName(env, clazz);
        }
        return oclType;
    }

    /**
     * Tries and infer the OCL type corresponding to the given java class name.
     * 
     * @param className
     *            The java class name for which we seek a corresponding OCL
     *            type.
     * @return The inferred OCL type, OCLAny if we could not infer something
     *         more sensible, or the simple class name if we could not even
     *         parse it into an accessible classpath Class.
     */
    private static String inferOCLType(String className) {
        String actualClass = className;

        // take care of the generic separately
        final int parameterIndex = actualClass.indexOf('<');
        String[] typeParameters = null;
        if (parameterIndex != -1) {
            typeParameters = actualClass.substring(parameterIndex + 1, actualClass.indexOf('>')).split(COMMA);
            actualClass = actualClass.substring(0, parameterIndex);
        }

        // Take care of the erasure
        final String erasure = inferOCLTypeNoGeneric(actualClass);

        // Then of the type parameter(s), if any
        if (typeParameters != null) {
            // We only accept a single type parameter
            final String typeParameter = typeParameters[0];
            // We've used a generic collection content of "OclAny", just replace
            // it in the signature.
            return erasure.replace("OclAny", inferOCLTypeNoGeneric(typeParameter)); //$NON-NLS-1$
        }
        return erasure;
    }

    /**
     * Tries and infer the OCL type corresponding to the given java class name,
     * having taken out the type parameters.
     * 
     * @param className
     *            The java class name for which we seek a corresponding OCL
     *            type.
     * @return The inferred OCL type, OCLAny if we could not infer something
     *         more sensible, or the simple class name if we could not even
     *         parse it into an accessible classpath Class.
     */
    private static String inferOCLTypeNoGeneric(String className) {
        // Can we find a corresponding class?
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // Ignore this, clazz will be null
        }

        if (clazz != null) {
            return inferOCLType(clazz);
        }

        // Not much choice here
        return className.substring(className.lastIndexOf('.') + 1);
    }
}
