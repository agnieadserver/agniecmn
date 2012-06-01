package com.agnie.gwt.helper;

import com.agnie.gwt.helper.marker.OverlayType;
import com.agnie.gwt.helper.marker.OverlayField;

@OverlayType
public class App {
        @OverlayField
        private String  name;
        @OverlayField
        private int     age;
        private String  test;

        /**
         * @return the name
         */
        public String getName() {
                return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
                this.name = name;
        }

        /**
         * @return the age
         */
        public int getAge() {
                return age;
        }

        /**
         * @param age
         *            the age to set
         */
        public void setAge(int age) {
                this.age = age;
        }

        /**
         * @return the test
         */
        public String getTest() {
                return test;
        }
        
        
        /**
         * @param test
         *            the test to set
         */
        public void setTest(String test) {
                this.test = test;
        }

}
