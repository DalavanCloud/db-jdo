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

/**
 * A JavaMethod instance represents a method declared by a class. It allows
 * to get detailed information about the method such as name, modifiers,
 * return type, parameters, and the declaring class.
 * <p>
 * Different environments (runtime, enhancer, development) will have
 * different JavaMethod implementations to provide answers to the various
 * methods. 
 * 
 * @author Michael Bouschen
 * @version JDO 2.0
 */
public interface JavaMethod extends JavaMember 
{
    /**
     * Returns the JavaType representation of the method return type.
     * @return method return type.
     */
    public JavaType getReturnType();

    /**
     * Returns an array of JavaType instances that represent the formal
     * parameter types, in declaration order, of the method represented by
     * this JavaMethod instance.
     * @return the types of teh formal parameters.
     */
    public JavaType[] getParameterTypes();
}
