package edu.eci.arsw.ecistaurant.security;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "llavesecreta123456789";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAzijp/BVDwddx/VxZZACdrQRb7kvBVFFML1545rs9YOlXaxiI\n" +
            "xrQGMVd8INDERtO1OhivgUt18UQWE2alTJyJnWDtHjFB3qxjrqGFFeO508Wtitqg\n" +
            "6nVlHlFxvadQfExBHZyayHjNMI5VS1GXDLYSmqQpLDo30Tcsn/UeY3iwiI00EjTV\n" +
            "3TjvM82HKvAzbHzn4Mwm45zGBoOytb3OW+9fiLTbKQk6OiiewUPtxCHm9c+WBq9H\n" +
            "GQaUuASCcxLE4gjfLIkFxbKeFT1EATnLESJm5wRg7JZzos71ck0rqFpcBeV1hwE6\n" +
            "q8YxaQqSc7BS1uYQnGKhepV2qedo+N9rnuUYpQIDAQABAoIBAHTP/fSV3/SfrLkQ\n" +
            "cJ4XM0BrreNSDVNHVVGpx/uEsHyO+rd2CVn9e1zN40l8wRoYryEiB5/NMCDqoU+Q\n" +
            "46yLLfZ8ix6GbnpLV5gFcTE7/XkfEvwQcQkoR4lsQHRjr1keo+6xY4qf2DPhEjdB\n" +
            "HcNYDyaMScADpMjo6GIiM3vNUMmtpCHoTDcOTUH4fSq+EBIy+P7M9/V5MaXsJHDg\n" +
            "HiJkOX3r0Swo0CK1JP/VtwiXF0LjNNX08Bnflq/n5IEqvEASYqbpa10H72UCZrYh\n" +
            "dowhm4SV2RzRzLRVMXs+lnAfdmseL0d6PWMFZYBAP91Giosu1azbAw0uarkpqR9h\n" +
            "wMlqsCkCgYEA6teP8ZDn3b96PgAh2734MrrxHAPar22oP7mm1NZGIFSKw0D0Z3uw\n" +
            "pbVrkSbCzY4AsrsHiv4dPv83KCuQ3EeZG49bopxfnY9P/2oNEX6zkbwJ6fAgHglk\n" +
            "8dbOTfODcSdWGXAw7Is1uIluJ7cqrvJCm9Bp3Blrbh+j3+nCTn1ihb8CgYEA4LvR\n" +
            "7Be9ZceCbr6al0UMKl/K6098xMyv+TBsvgYXzJH0WrkQj4uKhQOp/LCv/m+/K+wU\n" +
            "OzzfiqSyBa7ofp+2hMYHxaUzGAnvAdNpsLkUUUkGzpqMTUG1wJhdgGKN0vUHtir2\n" +
            "DO3m9cfQlu4PJfFJSo2CAK4TqAAGDZvXP9O1YpsCgYEA0XJjzNyiDyGBEt7nU6ZA\n" +
            "QG5+L7A4UBGK5xra8iQQ8zCzeaTq1Yh5abE+OHzLbKzjboRkG9C7oL3aIZeJYQzy\n" +
            "PqRC4+p3VHzGqlOZSCsqWCXO6bfhzSgvwYkFAnzWlAqzPYjT+YPG68iWWBGCnfku\n" +
            "8uyUHVsGd3b8nu1LANnPDt0CgYEAzaFNvVcjh8N7x1VEInGdZhBsnc8HfRNFWR9E\n" +
            "AMOWdyp9iZ2YV5/4ySjHG12YS5l4NPo4sNNMP5HfCnA2b9Y3rVl9tw0nqTE9rlAB\n" +
            "fyB6RL4rKlQjWHVtlqJTu6tx1bs6qSa6KK5uDCoe5FtDJRaYV00PzQt7FNegskcM\n" +
            "N2zBpU0CgYBr07DjHQSv5YLPp6zNLThkTK1WusFwegiOkIwVZfUOcwqrQ5KWv7Ae\n" +
            "4O/rAWSjPMKblOZAkJf3pqIiDLwJDaz8z9wCcNAseFZPd8XJuagX6tthueW+bEoO\n" +
            "q9+Oxc2kZ++FwHADFHiT95FfX3dsYsG3Bv47rl6WNWZVYEOuCp6KeA==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzijp/BVDwddx/VxZZACd\n" +
            "rQRb7kvBVFFML1545rs9YOlXaxiIxrQGMVd8INDERtO1OhivgUt18UQWE2alTJyJ\n" +
            "nWDtHjFB3qxjrqGFFeO508Wtitqg6nVlHlFxvadQfExBHZyayHjNMI5VS1GXDLYS\n" +
            "mqQpLDo30Tcsn/UeY3iwiI00EjTV3TjvM82HKvAzbHzn4Mwm45zGBoOytb3OW+9f\n" +
            "iLTbKQk6OiiewUPtxCHm9c+WBq9HGQaUuASCcxLE4gjfLIkFxbKeFT1EATnLESJm\n" +
            "5wRg7JZzos71ck0rqFpcBeV1hwE6q8YxaQqSc7BS1uYQnGKhepV2qedo+N9rnuUY\n" +
            "pQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
