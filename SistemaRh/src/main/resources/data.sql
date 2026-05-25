-- ============================================================
-- Dados iniciais para desenvolvimento — Sistema RH
-- Executado automaticamente no startup (banco H2 em memória)
-- ============================================================

INSERT INTO funcionarios (nome_funcionario, cpf, email, telefone_primario, telefone_segundario, cargo, nivel_hierarquico, status_funcionario)
VALUES
  ('Ana Beatriz Costa', '529.982.247-25', 'ana.costa@empresa.com', '(11) 99999-0001', NULL, 'Desenvolvedora Frontend', 'PLENO', 'ATIVO'),
  ('Carlos Eduardo Lima', '987.654.321-00', 'carlos.lima@empresa.com', '(21) 98888-0002', '(21) 97777-0002', 'Gerente de Projetos', 'GERENTE', 'ATIVO'),
  ('Fernanda Oliveira', '111.444.777-35', 'fernanda.oliveira@empresa.com', '(31) 96666-0003', NULL, 'Analista de RH', 'SENIOR', 'FERIAS'),
  ('Roberto Alves Santos', '853.513.468-93', 'roberto.santos@empresa.com', '(41) 95555-0004', NULL, 'Estagiário de TI', 'ESTAGIARIO', 'ATIVO'),
  ('Juliana Mendes', '369.333.878-79', 'juliana.mendes@empresa.com', '(51) 94444-0005', '(51) 93333-0005', 'Diretora Financeira', 'DIRETOR', 'ATIVO'),
  ('Marcos Pereira', '168.995.350-00', 'marcos.pereira@empresa.com', '(61) 92222-0006', NULL, 'Suporte Técnico', 'JUNIOR', 'AFASTADO'),
  ('Patricia Souza', '701.003.340-01', 'patricia.souza@empresa.com', '(71) 91111-0007', NULL, 'Scrum Master', 'ESPECIALISTA', 'ATIVO'),
  ('Diego Ferreira', '456.789.123-00', 'diego.ferreira@empresa.com', '(81) 90000-0008', NULL, 'DevOps Engineer', 'SENIOR', 'ATIVO');
