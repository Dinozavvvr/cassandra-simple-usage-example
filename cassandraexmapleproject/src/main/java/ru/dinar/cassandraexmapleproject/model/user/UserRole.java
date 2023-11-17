package ru.dinar.cassandraexmapleproject.model.user;

/**
 * Роль пользователей
 */
public enum UserRole {

    /** Пользователь системы (Базовый функционал) */
    USER,
    /** Лектор (Функционал доступный только лекторам) */
    LECTOR,
    /** Студент (Функционал доступный только студентам) */
    STUDENT

}
