package quill

import io.getquill.{PostgresJdbcContext, SnakeCase}

class DbContext extends PostgresJdbcContext(SnakeCase, "database")