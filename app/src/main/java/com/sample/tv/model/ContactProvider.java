/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.tv.model;

import android.content.Context;

import com.sample.tv.R;

import java.util.ArrayList;
import java.util.List;


/*
 * This class loads videos from a backend and saves them into a HashMap
 */
public class ContactProvider {


    private static List<Contact> contactList;
    private static Context sContext;

    private static int[] head = {R.drawable.avater1, R.drawable.avater2, R.drawable.avater3, R.drawable.avater4, R
            .drawable.avater5, R.drawable.avater6, R.drawable.avater7, R.drawable.avater8, R.drawable.avater9, R
            .drawable.avater10, R.drawable.avater11, R.drawable.avater12};


    private static String[] names = {"梦洁", "雅静", "韵寒", "莉姿", "沛玲", "欣妍", "歆瑶", "凌菲", "靖瑶", "瑾萱", "芳蕤", "若华"};


    private static String[] phones = {"18618188630", "18158103936", "18620145337", "15116333186", "18618188630",
            "18158103936", "18620145337", "15116333186", "18618188630", "18158103936", "18620145337", "18767106408"};

    private static String[] uids = {"8432010", "8213001", "8432009", "8425004", "8432010", "8213001", "8432009",
            "8425004", "8432010", "8213001", "8432009", "30001"};

    public static void setContext(Context context) {
        if (sContext == null)
            sContext = context;
    }

    public static String getUidByPhone(String number) {
        for (int i = 0; i < phones.length; ++i) {
            String phone = phones[i];
            if (phone.equals(number)) {
                return uids[i];
            }
        }
        return null;
    }

    public static List<Contact> getContactList() {
        buildContact();
        return contactList;
    }

    public static List<Contact> buildContact() {
        if (null != contactList) {
            return contactList;
        }
        contactList = new ArrayList<Contact>();
        for (int i = 0; i < 12; ++i) {
            contactList.add(buildContactInfo(uids[i], phones[i], names[i], head[i]));
        }
        return contactList;
    }

    private static Contact buildContactInfo(String uid, String phone, String name, int resId) {
        Contact contact = new Contact();
        contact.setUid(uid);
        contact.setPhone(phone);
        contact.setName(name);
        contact.setHeadResId(resId);
        return contact;
    }
}
