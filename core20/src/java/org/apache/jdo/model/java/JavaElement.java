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
 * This is the super interface for all the JavaModel elements, such as 
 * JavaField, JavaType, etc.
 * 
 * @author Michael Bouschen
 * @since JDO 2.0
 */
public interface JavaElement 
{

    /**
     * Returns the environment specific instance wrapped by this JavaModel
     * element. E.g. a runtime implementation of JavaField might use a
     * java.lang.reflect.Field instance as the underlying object.
     * @return the environment specific instance wrapped by this JavaModel
     * element.
     */
    public Object getUnderlyingObject();
    
}
