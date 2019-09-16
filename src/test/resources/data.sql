INSERT INTO pauta(id, assunto) values (1, 'Assunto');

INSERT INTO sessao(id, pauta_id, data_hora_abertura, data_hora_fechamento) values (1, 1, now(), now());
INSERT INTO sessao(id, pauta_id, data_hora_abertura, data_hora_fechamento) values (2, 1, now(), now());

INSERT INTO voto(id, sessao_id, cpf_associado, escolha) values (1, 2, '33253332144', 0);
INSERT INTO voto(id, sessao_id, cpf_associado, escolha) values (2, 2, '33253332144', 1);