package com.shiro.entity;

import java.util.ArrayList;
import java.util.List;

public class SysSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysSettingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("Id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("Id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSyskeyIsNull() {
            addCriterion("sysKey is null");
            return (Criteria) this;
        }

        public Criteria andSyskeyIsNotNull() {
            addCriterion("sysKey is not null");
            return (Criteria) this;
        }

        public Criteria andSyskeyEqualTo(String value) {
            addCriterion("sysKey =", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyNotEqualTo(String value) {
            addCriterion("sysKey <>", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyGreaterThan(String value) {
            addCriterion("sysKey >", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyGreaterThanOrEqualTo(String value) {
            addCriterion("sysKey >=", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyLessThan(String value) {
            addCriterion("sysKey <", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyLessThanOrEqualTo(String value) {
            addCriterion("sysKey <=", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyLike(String value) {
            addCriterion("sysKey like", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyNotLike(String value) {
            addCriterion("sysKey not like", value, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyIn(List<String> values) {
            addCriterion("sysKey in", values, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyNotIn(List<String> values) {
            addCriterion("sysKey not in", values, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyBetween(String value1, String value2) {
            addCriterion("sysKey between", value1, value2, "syskey");
            return (Criteria) this;
        }

        public Criteria andSyskeyNotBetween(String value1, String value2) {
            addCriterion("sysKey not between", value1, value2, "syskey");
            return (Criteria) this;
        }

        public Criteria andSysnameIsNull() {
            addCriterion("sysName is null");
            return (Criteria) this;
        }

        public Criteria andSysnameIsNotNull() {
            addCriterion("sysName is not null");
            return (Criteria) this;
        }

        public Criteria andSysnameEqualTo(String value) {
            addCriterion("sysName =", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameNotEqualTo(String value) {
            addCriterion("sysName <>", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameGreaterThan(String value) {
            addCriterion("sysName >", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameGreaterThanOrEqualTo(String value) {
            addCriterion("sysName >=", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameLessThan(String value) {
            addCriterion("sysName <", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameLessThanOrEqualTo(String value) {
            addCriterion("sysName <=", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameLike(String value) {
            addCriterion("sysName like", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameNotLike(String value) {
            addCriterion("sysName not like", value, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameIn(List<String> values) {
            addCriterion("sysName in", values, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameNotIn(List<String> values) {
            addCriterion("sysName not in", values, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameBetween(String value1, String value2) {
            addCriterion("sysName between", value1, value2, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysnameNotBetween(String value1, String value2) {
            addCriterion("sysName not between", value1, value2, "sysname");
            return (Criteria) this;
        }

        public Criteria andSysvalueIsNull() {
            addCriterion("sysValue is null");
            return (Criteria) this;
        }

        public Criteria andSysvalueIsNotNull() {
            addCriterion("sysValue is not null");
            return (Criteria) this;
        }

        public Criteria andSysvalueEqualTo(String value) {
            addCriterion("sysValue =", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueNotEqualTo(String value) {
            addCriterion("sysValue <>", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueGreaterThan(String value) {
            addCriterion("sysValue >", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueGreaterThanOrEqualTo(String value) {
            addCriterion("sysValue >=", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueLessThan(String value) {
            addCriterion("sysValue <", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueLessThanOrEqualTo(String value) {
            addCriterion("sysValue <=", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueLike(String value) {
            addCriterion("sysValue like", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueNotLike(String value) {
            addCriterion("sysValue not like", value, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueIn(List<String> values) {
            addCriterion("sysValue in", values, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueNotIn(List<String> values) {
            addCriterion("sysValue not in", values, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueBetween(String value1, String value2) {
            addCriterion("sysValue between", value1, value2, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSysvalueNotBetween(String value1, String value2) {
            addCriterion("sysValue not between", value1, value2, "sysvalue");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSysdescIsNull() {
            addCriterion("sysDesc is null");
            return (Criteria) this;
        }

        public Criteria andSysdescIsNotNull() {
            addCriterion("sysDesc is not null");
            return (Criteria) this;
        }

        public Criteria andSysdescEqualTo(String value) {
            addCriterion("sysDesc =", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescNotEqualTo(String value) {
            addCriterion("sysDesc <>", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescGreaterThan(String value) {
            addCriterion("sysDesc >", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescGreaterThanOrEqualTo(String value) {
            addCriterion("sysDesc >=", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescLessThan(String value) {
            addCriterion("sysDesc <", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescLessThanOrEqualTo(String value) {
            addCriterion("sysDesc <=", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescLike(String value) {
            addCriterion("sysDesc like", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescNotLike(String value) {
            addCriterion("sysDesc not like", value, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescIn(List<String> values) {
            addCriterion("sysDesc in", values, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescNotIn(List<String> values) {
            addCriterion("sysDesc not in", values, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescBetween(String value1, String value2) {
            addCriterion("sysDesc between", value1, value2, "sysdesc");
            return (Criteria) this;
        }

        public Criteria andSysdescNotBetween(String value1, String value2) {
            addCriterion("sysDesc not between", value1, value2, "sysdesc");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}