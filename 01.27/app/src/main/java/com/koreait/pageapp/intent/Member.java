package com.koreait.pageapp.intent;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable {
    private String m_id;
    private String m_pass;
    private String m_name;
    
    // 복원하기 위해 일반 생성자 만들기
    public Member() {
    }

    //받기위해 데이터 복원
    protected Member(Parcel in) {
        m_id = in.readString();
        m_pass = in.readString();
        m_name = in.readString();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_pass() {
        return m_pass;
    }

    public void setM_pass(String m_pass) {
        this.m_pass = m_pass;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //보내기 위해 직렬화 후 저장
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(m_id);
        dest.writeString(m_pass);
        dest.writeString(m_name);
    }
}
