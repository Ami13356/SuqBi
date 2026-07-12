<?php
namespace App\Config;

class Validator
{
    private static array $errors = [];

    public static function reset(): void
    {
        self::$errors = [];
    }

    public static function isEmpty(string $data, string $key, string $msg = "Ce champ est obligatoire"): void
    {
        if (empty($data)) {
            self::$errors[$key] = $msg;
        }
    }

    public static function minLength(string $data, string $key, int $min, string $msg): void
    {
        if (!empty($data) && strlen($data) < $min) {
            self::$errors[$key] = $msg;
        }
    }

    public static function isEmail(string $data, string $key, string $msg = "Email invalide"): void
    {
        if (!empty($data) && !filter_var($data, FILTER_VALIDATE_EMAIL)) {
            self::$errors[$key] = $msg;
        }
    }

    public static function getErrors(): array
    {
        return self::$errors;
    }

    public static function validate(): bool
    {
        return count(self::$errors) == 0;
    }
}
