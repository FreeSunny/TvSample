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

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Movie class represents video entity with title, description, image thumbs and video url.
 */
public class Contact implements Parcelable {

    private String uid;

    private String phone;

    private int headResId;

    private String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHeadResId() {
        return headResId;
    }

    public void setHeadResId(int headResId) {
        this.headResId = headResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact() {

    }


    public Contact(Parcel in) {
        uid = in.readString();
        phone = in.readString();
        headResId = in.readInt();
        name = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(phone);
        dest.writeInt(headResId);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("Contact{");
        sb.append("uid=" + uid);
        sb.append(", phone='" + phone + '\'');
        sb.append(", headResId='" + headResId + '\'');
        sb.append(", name='" + name + '\'');
        sb.append('}');
        return sb.toString();
    }

    public static final Creator CREATOR = new Creator() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
