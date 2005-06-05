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
 * A JavaProperty instance represents a JavaBeans property.
 * 
 * @author Michael Bouschen
 * @version JDO 2.0
 */
public interface JavaProperty extends JavaField
{
    /**
     * Returns the JavaMethod representation of the getter method for this
     * JavaProperty. If there is no getter method for this JavaProperty
     * (i.e. the property is write-only), then the method returns
     * <code>null</code>.
     * @return the getter method if available; or <code>null</code>
     * otherwise.
     */
    public JavaMethod getGetterMethod();

    /**
     * Returns the JavaMethod representation of the setter method for this
     * JavaProperty. If there is no setter method for this JavaProperty
     * (i.e. the property is read-only), then the method returns
     * <code>null</code>.
     * @return the setter method if available; or <code>null</code>
     * otherwise.
     */
    public JavaMethod getSetterMethod();
}
