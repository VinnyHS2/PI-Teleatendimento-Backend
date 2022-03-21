package com.pi.teleatendimento.hibernate.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL10Dialect;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;


public class PostgreSQLDialect extends PostgreSQL10Dialect {

	public PostgreSQLDialect() {
		super();
		this.registerHibernateType(Types.OTHER, JsonNodeBinaryType.class.getName());
	}
}
