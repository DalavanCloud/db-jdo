/*
 * Copyright 2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package org.apache.jdo.model.java;

import org.apache.jdo.model.jdo.JDOField;

/**
 * A JavaField instance represents a field declared by a class. It allows
 * to get detailed information about the field such as name, modifiers,
 * type, declaring class and the JDO meta data for the field (if
 * available). 
 * <p>
 * Different environments (runtime, enhancer, development) will have
 * different JavaField implementations to provide answers to the various
 * methods. 
 * 
 * @author Michael Bouschen
 * @since JDO 1.0.1
 * @version JDO 2.0
 */
public interface JavaField extends JavaMember 
{
    /**
     * Returns the corresponding JDOField instance, if the JDOModel
     * provides any JDO metadata for the field represented by this
     * JavaField. If there is no corresponding JDOField representation, the
     * method returns <code>null</code>. 
     * <p>
     * A <code>null</code> result means the declaring class is not
     * persistence capable or the field represented by this JavaField is
     * not managed. Note, a non-<code>null</code> result does not
     * necessarily mean the field is managed. The JDO metadata might define
     * the persistence-modifier of this field as <code>none</code>. Then
     * the JDOModel provides a JDOField instance which is returned by this
     * method. You can call method  
     * {@link org.apache.jdo.model.jdo.JDOField#isManaged()} on a 
     * non-<code>null</code> result to verify that this JavaField
     * represents a managed field. 
     * @return the corresponding JDOField instance (if available);
     * <code>null</code> otherwise.
     */
    public JDOField getJDOField();
}
