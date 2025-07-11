/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.demo.client.backend;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Singleton;

/**
 * @author Pandurang Patil 26-Jan-2017 - 7:26:14 pm
 *
 */
@Singleton
public class Cache {
    public List<String> list = new ArrayList<String>();

    public List<String> getList() {
        return list;
    }

    public void add(String person) {
        list.add(person);
    }

    public void delete(String person) {
        list.remove(person);
    }
}
