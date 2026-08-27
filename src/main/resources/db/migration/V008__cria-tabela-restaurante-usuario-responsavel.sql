CREATE TABLE `restaurante_usuario_responsavel` (
    `restaurante_id` bigint NOT NULL,
    `usuario_id` bigint NOT NULL,
    PRIMARY KEY (`restaurante_id`,`usuario_id`),
    KEY `fk_rest_usuario_usuario` (`usuario_id`),
    CONSTRAINT `fk_rest_usuario_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
    CONSTRAINT `fk_rest_usuario_restaurante` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;