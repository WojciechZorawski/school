package db.changelog

databaseChangeLog {

  changeSet(id: 'Create teacher table', author: 'wzorawski') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: "teacher")
      }
    }
    createTable(tableName: 'teacher') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }
      column(name: 'name', type: 'varchar(20)') {}
      column(name: 'last_name', type: 'varchar(30)') {}
      column(name: 'age', type: 'integer') {}
      column(name: 'profession', type: 'varchar(25)')
    }
    createSequence(sequenceName: 'SEQ_teacher', startValue: '1')
  }

  changeSet(id: 'Create register table', author: 'wzorawski') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: "register")
      }
    }
    createTable(tableName: 'register') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }
      column(name: 'teacher_id', type: 'bigint') {}
    }
    addForeignKeyConstraint(constraintName: 'FK_register_teacher_id',
        baseTableName: 'register', baseColumnNames: 'teacher_id',
        referencedTableName: 'teacher', referencedColumnNames: 'id')

    createSequence(sequenceName: 'SEQ_register', startValue: '1')
  }

  changeSet(id: 'Create student table', author: 'wzorawski') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: "student")
      }
    }
    createTable(tableName: 'student') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }
      column(name: 'name', type: 'varchar(20)') {}
      column(name: 'last_name', type: 'varchar(30)') {}
      column(name: 'email', type: 'varchar(50)') {}
      column(name: 'date_of_birth', type: 'date') {}
      column(name: 'age', type: 'integer') {}
      column(name: 'register_id', type: 'bigint') {}
      column(name: 'sex', type: 'varchar(1)') {}
    }
    addForeignKeyConstraint(constraintName: 'FK_student_register_id',
        baseTableName: 'student', baseColumnNames: 'register_id',
        referencedTableName: 'register', referencedColumnNames: 'id')
    createSequence(sequenceName: 'SEQ_student', startValue: '1')
  }

  changeSet(id: 'Create grade table', author: 'wzorawski') {
    preConditions(onFail: 'MARK_RAN') {
      not() {
        tableExists(tableName: "grade")
      }
    }
    createTable(tableName: 'grade') {
      column(name: 'id', type: 'bigint', autoIncrement: true) {
        constraints(nullable: false, primaryKey: true)
      }
      column(name: 'date', type: 'date') {}
      column(name: 'grade', type: 'integer') {}
      column(name: 'subject', type: 'varchar(25)') {}
      column(name: 'student_id', type: 'bigint') {}
    }
    addForeignKeyConstraint(constraintName: 'FK_grade_student_id',
        baseTableName: 'grade', baseColumnNames: 'student_id',
        referencedTableName: 'student', referencedColumnNames: 'id')

    createSequence(sequenceName: 'SEQ_grade', startValue: '1')
  }

  changeSet(id: 'Add description column to grade table', author: 'wzorawski') {
    preConditions(onFail: 'MARK_RAN') {
      tableExists(tableName: "grade")
    }
    addColumn(tableName: 'grade') {
      column(name: 'description', type: 'varchar(50)')
    }
  }
}
