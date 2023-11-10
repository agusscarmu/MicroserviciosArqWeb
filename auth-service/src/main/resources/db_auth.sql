-- Volcado de datos para la tabla `USER`
INSERT INTO `user` (`id`, `apellido`, `email`, `nombre`, `password`) VALUES
                                                                         (1, 'Liceaga', 'lice1111@gmail', 'Hector', 'hector1234'),
                                                                         (2, 'Yanez', 'yanez2222@gmail', 'Sergio', 'sergio1234');


-- Volcado de datos para la tabla `account`
INSERT INTO `account` (`saldo`, `id`) VALUES
                                          (10, 1),
                                          (20, 2);

-- Volcado de datos para la tabla `authority`
INSERT INTO `authority` (`name`) VALUES
                                     ('hector'),
                                     ('sergio');

-- Volcado de datos para la tabla `rel_user__account`
INSERT INTO `rel_user__account` (`account_id`, `user_id`) VALUES
                                                              (1, 1),
                                                              (2, 2);

-- Volcado de datos para la tabla `rel_user__authority`
INSERT INTO `rel_user__authority` (`user_id`, `authority_id`) VALUES
                                                                  (1, 'hector'),
                                                                  (2, 'sergio');


