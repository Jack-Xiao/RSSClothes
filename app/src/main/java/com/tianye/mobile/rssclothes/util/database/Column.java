package com.tianye.mobile.rssclothes.util.database;

/**
 * 列类:  拥有属性： 列名、列特性、列类型
 *
 *
 *
 */
public class Column {

    public static enum Constraint {
        UNIQUE("UNIQUE"), NOT("NOT"), NULL("NULL"), CHECK("CHECK"), FOREIGN_KEY("FOREIGN KEY"),
        PRIMARY_KEY("PRIMARY KEY");

        private String value;

        private Constraint(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            //return super.toString();
            return value;
        }
    }

        public static enum DataType{
            NULL, INTEGER, REAL, TEXT, BLOB
        }

        private String mColumnName;

        private Constraint mConstraint;

        private DataType mDataType;

        public Column(String columnName, Constraint constraint, DataType dataType){
            mColumnName = columnName;
            mConstraint = constraint;
            mDataType = dataType;
        }

    public String getColumnName() {
        return mColumnName;
    }

    public Constraint getConstraint() {
        return mConstraint;
    }

    public DataType getDataType() {
        return mDataType;
    }
}
