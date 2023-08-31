Usuario: Rold 
Senha: EsseAlunoVaiTirar100 

Explicação de todo código do projeto: 


Classe Controller
A classe Controller é responsável por intermediar as interações entre as diferentes partes do sistema de controle de despesas. Ela gerencia o fluxo de execução, interações com o usuário e manipulação dos dados, proporcionando uma interface intuitiva para os usuários gerenciarem suas despesas e registros.

Métodos Principais
exibirTelaLogin()
Este método exibe uma tela de autenticação, solicitando que o usuário insira seu nome de usuário e senha. Ele verifica a autenticidade das credenciais fornecidas por meio do GerenciadorUsuarios, permitindo o acesso ao sistema caso as credenciais sejam válidas.

exibirMenuPrincipal()
Este método apresenta o menu principal do sistema, permitindo que os usuários escolham entre diversas opções, incluindo cadastrar despesas, anotar pagamentos, gerenciar tipos de despesa, gerenciar usuários, listar despesas e sair do programa. Ele interage com diferentes partes do sistema, como Despesa, GerenciadorTiposDespesa e GerenciadorUsuarios, garantindo uma experiência completa ao usuário.

cadastrarDespesa()
Esse método guia o usuário pelo processo de criação de uma nova despesa, solicitando informações como descrição, valor, data de vencimento e categoria. Ele também permite que o usuário selecione ou crie um tipo de despesa, bem como escolha uma categoria predefinida ou crie uma nova. A despesa criada é adicionada à lista de despesas e salva em um arquivo para persistência.

selecionarOuCriarTipoDespesa()
Esse método oferece ao usuário a opção de selecionar um tipo de despesa existente ou criar um novo. Ele interage com o GerenciadorTiposDespesa, permitindo uma escolha flexível e personalizada dos tipos de despesa associados às despesas cadastradas.

Fluxo Principal
A classe Controller é a espinha dorsal do sistema, coordenando as diferentes funcionalidades e assegurando que a experiência do usuário seja coesa e intuitiva. Ao interagir com outras classes e gerenciadores, ela possibilita a manipulação eficaz de dados e a execução de tarefas essenciais no contexto do controle de despesas.

Execução do Programa
O método main é o ponto de entrada do programa, carregando os tipos de despesa e usuários, exibindo a tela de login e, posteriormente, o menu principal do sistema. Isso garante que o fluxo de execução comece de forma organizada e coerente.
*
*
*
*
*
*
*
*
Classe CategoriaDespesa
A classe abstrata CategoriaDespesa serve como uma estrutura base para a categorização de despesas no sistema de controle de despesas. Ela encapsula funcionalidades comuns relacionadas a categorias e fornece uma base para a implementação de categorias específicas.

Atributos
nome: Representa o nome da categoria de despesa.
despesas: Uma lista de despesas associadas a essa categoria.
Métodos Principais
adicionarDespesa(Despesa despesa)
Este método permite adicionar uma despesa à lista de despesas associadas a esta categoria.

calcularTotalDespesas()
Este método calcula e retorna o total de despesas associadas a esta categoria, somando os valores de todas as despesas presentes na lista.

getDescricaoCategoria()
Este é um método abstrato que deve ser implementado nas subclasses para fornecer uma descrição da categoria de despesa. Serve como uma maneira de identificar e descrever a categoria.

criarCategoria(String nome)
Este é um método estático que cria uma nova instância de CategoriaDespesa com base no nome fornecido. Se o nome corresponder a categorias predefinidas como "Transporte", "Eventual" ou "Supérfluo", ele retorna a categoria correspondente. Caso contrário, cria uma nova CategoriaDespesaCustomizada com o nome fornecido.

Fluxo de Uso
A classe CategoriaDespesa estabelece uma estrutura padrão para categorias de despesas, permitindo a extensão para categorias específicas, como CategoriaTransporte, CategoriaEventual, CategoriaSuperfluo e CategoriaDespesaCustomizada. Ela oferece métodos para gerenciar despesas associadas e calcular totais, bem como para criar categorias com base em nomes.
*
*
*
*
*
*
*
*
Classe CategoriaDespesaCustomizada
A classe CategoriaDespesaCustomizada é uma implementação concreta da classe abstrata CategoriaDespesa. Ela permite a criação de categorias personalizadas para despesas, além das categorias predefinidas como "Transporte", "Eventual" e "Supérfluo".

Construtor
CategoriaDespesaCustomizada(String nome)
Este construtor cria uma nova categoria customizada com o nome fornecido como parâmetro. Ele chama o construtor da classe base CategoriaDespesa para inicializar os atributos herdados.

Métodos
getDescricaoCategoria()
Este método sobrescrito fornece uma descrição da categoria customizada. Ele retorna uma string que indica que se trata de uma "Categoria Customizada" seguida pelo nome da categoria.

Fluxo de Uso
A classe CategoriaDespesaCustomizada é usada para criar categorias personalizadas que não correspondem às categorias predefinidas. Ao instanciar essa classe, você pode criar uma nova categoria de despesa com um nome específico. A descrição da categoria é fornecida através do método getDescricaoCategoria().
*
*
*
*
*
*
*
*
Classe CategoriaEventual
A classe CategoriaEventual é uma implementação concreta da classe abstrata CategoriaDespesa. Ela representa a categoria de despesas eventuais, que engloba gastos não recorrentes.

Construtor
CategoriaEventual()
O construtor dessa classe chama o construtor da classe base CategoriaDespesa, passando o nome "Eventual" como parâmetro. Isso inicializa o nome da categoria e cria uma nova lista de despesas associadas a essa categoria.

Métodos
getDescricaoCategoria()
Este método sobrescrito fornece uma descrição da categoria de despesas eventuais. Ele retorna uma string que descreve que a categoria é destinada a "Despesas eventuais e não recorrentes".

Fluxo de Uso
A classe CategoriaEventual é usada para representar a categoria de despesas eventuais, que engloba gastos não recorrentes e não previsíveis. A descrição da categoria é fornecida pelo método getDescricaoCategoria(), que pode ser utilizado para documentar e explicar a finalidade dessa categoria no sistema.
*
*
*
*
*
*
*
*
Classe CategoriaSuperfluo
A classe CategoriaSuperfluo é uma implementação concreta da classe abstrata CategoriaDespesa. Ela representa a categoria de despesas supérfluas, que engloba gastos não essenciais.

Construtor
CategoriaSuperfluo()
O construtor dessa classe chama o construtor da classe base CategoriaDespesa, passando o nome "Supérfluo" como parâmetro. Isso inicializa o nome da categoria e cria uma nova lista de despesas associadas a essa categoria.

Métodos
getDescricaoCategoria()
Este método sobrescrito fornece uma descrição da categoria de despesas supérfluas. Ele retorna uma string que descreve que a categoria é destinada a "Despesas supérfluas e não essenciais".

Fluxo de Uso
A classe CategoriaSuperfluo é usada para representar a categoria de despesas supérfluas, que abrange gastos não essenciais e dispensáveis. A descrição da categoria é fornecida pelo método getDescricaoCategoria(), que pode ser utilizado para documentar e explicar a finalidade dessa categoria no sistema.
*
*
*
*
*
*
*
*
Classe CategoriaTransporte
A classe CategoriaTransporte é uma implementação concreta da classe abstrata CategoriaDespesa. Ela representa a categoria de despesas relacionadas a transporte, englobando gastos ligados a deslocamentos e locomoção.

Construtor
CategoriaTransporte()
O construtor dessa classe chama o construtor da classe base CategoriaDespesa, passando o nome "Transporte" como parâmetro. Isso inicializa o nome da categoria e cria uma nova lista de despesas associadas a essa categoria.

Métodos
getDescricaoCategoria()
Este método sobrescrito fornece uma descrição da categoria de despesas de transporte. Ele retorna uma string que descreve que a categoria é destinada a "Despesas relacionadas a transporte".

Fluxo de Uso
A classe CategoriaTransporte é usada para representar a categoria de despesas de transporte, que abrange gastos relacionados a deslocamentos e locomoção. A descrição da categoria é fornecida pelo método getDescricaoCategoria(), que pode ser utilizado para documentar e explicar a finalidade dessa categoria no sistema.
*
*
*
*
*
*
*
*
Classe Despesa
A classe Despesa é uma das principais classes do sistema de controle de despesas. Ela representa uma despesa específica, contendo informações como descrição, valor, data de vencimento, categoria, tipo de despesa, categoria de despesa, valor restante (caso seja parcelada) e pagamentos associados.

Construtor
Despesa(String descricao, double valor, String dataVencimento, String categoria, TipoDespesa tipoDespesa, CategoriaDespesa categoriaDespesa)
Este construtor cria uma instância de Despesa com os seguintes parâmetros:

descricao: Descrição da despesa.
valor: Valor total da despesa.
dataVencimento: Data de vencimento da despesa.
categoria: Categoria da despesa.
tipoDespesa: Tipo de despesa associado.
categoriaDespesa: Categoria específica de despesa associada.
Métodos
Métodos de Definição de Propriedades
A classe Despesa possui métodos para definir propriedades como descrição, valor, data de vencimento, categoria e categoria de despesa. A implementação destes métodos não está presente no código fornecido, mas eles devem ser responsáveis por atribuir os valores passados como parâmetros às propriedades correspondentes da instância.

inicializarPagamentos()
Esse método inicializa a lista de pagamentos associados à despesa, criando uma nova lista vazia de pagamentos.

adicionarPagamento(Pagamento pagamento)
Este método adiciona um objeto Pagamento à lista de pagamentos associados a essa despesa.

Métodos de Acesso a Propriedades
A classe fornece métodos para acessar informações sobre a despesa, como sua descrição, valor total, valor restante, categoria, tipo de despesa, etc.

equals(Object o)
Este método verifica se duas instâncias de Despesa são iguais com base na descrição e data de vencimento. Se a descrição e a data de vencimento forem iguais, as instâncias são consideradas iguais.

hashCode()
Esse método calcula o código hash da instância com base na descrição e data de vencimento.

Métodos para Verificar e Marcar Pagamento
A classe possui métodos para verificar se a despesa está paga (estaPaga()) e para marcar a despesa como paga (marcarComoPaga()). Esses métodos precisam ser implementados para refletir a lógica de pagamento da despesa.

Métodos de Listagem de Despesas
A classe Despesa possui métodos estáticos para listar despesas pagas e em aberto, além de listar despesas por período. Esses métodos interagem com a classe Controller e outras classes para exibir informações sobre as despesas.

Métodos de Manipulação de Arquivos
A classe inclui métodos estáticos para salvar e carregar despesas a partir de arquivos. O método salvarDespesasEmArquivo() escreve uma lista de despesas em um arquivo, enquanto o método carregarDespesasDeArquivo() lê uma lista de despesas de um arquivo.
*
*
*
*
*
*
*
*
Classe ListarDespesasController
A classe ListarDespesasController é responsável por gerenciar a listagem, edição e exclusão de despesas no sistema de controle de despesas.

Métodos
listarDespesas()
Esse método exibe um menu que permite listar as despesas cadastradas, bem como realizar ações como edição, exclusão e listagem por período. Ele interage com o usuário por meio de caixas de diálogo do JOptionPane. O usuário pode selecionar uma despesa para editar ou excluir, além de ter a opção de listar despesas por período (pagas e em aberto) ou voltar ao menu principal.

editarDespesa(Despesa despesa)
Este método é chamado quando o usuário escolhe editar uma despesa. Ele exibe caixas de diálogo para permitir a edição da descrição, valor, data de vencimento, tipo de despesa e categoria da despesa. Após a edição, a despesa é atualizada na lista de despesas e no arquivo.

excluirDespesa(Despesa despesa)
Este método é chamado quando o usuário escolhe excluir uma despesa. Ele exibe uma caixa de diálogo de confirmação e, se o usuário confirmar a exclusão, a despesa é removida da lista de despesas e do arquivo.

getListaDeDespesas()
Este método retorna a lista de despesas do controlador principal. 
*
*
*
*
*
*
*
*
Classe MenuDespesas
A classe MenuDespesas é responsável por exibir um menu de opções relacionadas às despesas, permitindo a listagem, edição, exclusão e gerenciamento de tipos de despesa.

Métodos
listarDespesas(List<Despesa> despesas)
Este método exibe um menu que permite ao usuário selecionar entre listar todas as despesas, listar despesas pagas ou listar despesas pendentes. Dependendo da seleção, as despesas relevantes são exibidas em um novo menu.

exibirSubMenuDespesa(Despesa despesa)
Este método exibe um submenu de opções para uma despesa específica. As opções incluem editar a despesa, excluir a despesa, gerenciar o tipo de despesa associado à despesa ou voltar ao menu principal.

editarDespesa(Despesa despesa)
Este método ainda não está implementado, mas seria responsável por permitir a edição de uma despesa específica.

excluirDespesa(Despesa despesa)
Este método ainda não está implementado, mas seria responsável por permitir a exclusão de uma despesa específica.

gerenciarTipoDespesa(Despesa despesa)
Este método exibe um submenu para gerenciar os tipos de despesa. As opções incluem criar um novo tipo de despesa, listar tipos de despesa, editar um tipo de despesa, excluir um tipo de despesa ou voltar.

criarTipoDespesa()
Este método ainda não está implementado, mas seria responsável por permitir a criação de um novo tipo de despesa.

listarTiposDespesa()
Este método ainda não está implementado, mas seria responsável por listar os tipos de despesa existentes.

editarTipoDespesa()
Este método ainda não está implementado, mas seria responsável por permitir a edição de um tipo de despesa existente.

excluirTipoDespesa()
Este método ainda não está implementado, mas seria responsável por permitir a exclusão de um tipo de despesa existente. 
*
*
*
*
*
*
*
*
Classe Pagamento
A classe Pagamento representa um pagamento realizado, contendo informações sobre a data e o valor do pagamento. É utilizada para registrar pagamentos relacionados a despesas ou transações específicas.

Atributos:

dataPagamento: Uma string que armazena a data em que o pagamento foi efetuado.
valorPagamento: Um número decimal que indica o valor do pagamento realizado.
Construtor:

Pagamento(String dataPagamento, double valorPagamento): Cria um objeto Pagamento com a data e o valor fornecidos.
Métodos:

getDataPagamento(): Retorna a data do pagamento.
setDataPagamento(String dataPagamento): Atualiza a data do pagamento.
getValorPagamento(): Retorna o valor do pagamento.
setValorPagamento(double valorPagamento): Atualiza o valor do pagamento.
A classe Pagamento desempenha um papel fundamental no sistema de controle de despesas, permitindo o registro e o rastreamento dos pagamentos feitos em relação a diferentes despesas ou transações.
*
*
*
*
*
*
*
*
Classe PagamentoManager
A classe PagamentoManager é responsável por gerenciar as operações relacionadas a pagamentos no sistema de controle de despesas. Ela permite registrar pagamentos para despesas e realizar ações associadas a eles.

Métodos:

anotarPagamento(List<Despesa> listaDeDespesas, GerenciadorTiposDespesa gerenciadorTiposDespesa, String arquivoDespesas): Este método permite ao usuário registrar um pagamento para uma despesa específica. Ele verifica se a despesa já foi paga, solicita o valor e a data do pagamento e registra o pagamento na lista de pagamentos da despesa. Também atualiza o valor restante da despesa após o pagamento.

selecionarPagavelParaPagamento(List<Despesa> listaDeDespesas): Esse método permite ao usuário escolher uma despesa da lista de despesas disponíveis para registrar um pagamento. Ele exibe uma caixa de diálogo com as opções de despesas e retorna a despesa selecionada ou null se nenhuma for selecionada.

selecionarTipoDespesa(GerenciadorTiposDespesa gerenciadorTiposDespesa): Esse método é usado para selecionar um tipo de despesa existente ou criar um novo tipo de despesa. Ele exibe uma caixa de diálogo com as opções e retorna o tipo de despesa selecionado ou criado.

A classe PagamentoManager desempenha um papel fundamental na interação do usuário com o sistema, permitindo o registro de pagamentos e a seleção de tipos de despesa, contribuindo para a funcionalidade geral de gerenciamento de despesas. 
*
*
*
*
*
*
*
*
Interface Pagavel
A interface Pagavel define um conjunto de métodos que as classes devem implementar para representar itens que podem ser pagos, como despesas. Ela permite que diferentes tipos de itens possam ser tratados de maneira uniforme no contexto do sistema de controle de despesas.

Métodos:

getValor(): Retorna o valor total do item a ser pago.

getDescricao(): Retorna a descrição do item, o que ajuda a identificá-lo.

getDataVencimento(): Retorna a data de vencimento do item.

estaPaga(): Verifica se o item foi pago ou não.

marcarComoPaga(): Marca o item como pago.

A interface Pagavel é fundamental para garantir que diferentes tipos de objetos que podem ser pagos se comportem de maneira consistente no sistema. Classes como Despesa podem implementar essa interface para que os métodos relacionados a pagamentos possam ser aplicados a elas de forma padronizada. 
*
*
*
*
*
*
*
*
Classe TipoDespesa
A classe TipoDespesa representa os diferentes tipos de despesa que podem ser associados às despesas cadastradas no sistema de controle de despesas.

Atributos:

nome: O nome do tipo de despesa.
Métodos:

Construtores: A classe possui dois construtores, um que aceita o nome como parâmetro e outro vazio.

getNome(): Retorna o nome do tipo de despesa.

setNome(String nome): Define o nome do tipo de despesa.
*
*
*
*
*
*
*
*
Classe GerenciadorTiposDespesa
A classe GerenciadorTiposDespesa é responsável por gerenciar os diferentes tipos de despesa no sistema, incluindo a criação, listagem e exclusão dos tipos de despesa.

Métodos:

criarTipoDespesa(): Permite criar um novo tipo de despesa, solicitando o nome ao usuário e adicionando-o à lista de tipos de despesa.

listarTiposDespesa(): Lista todos os tipos de despesa existentes no sistema.

excluirTipoDespesa(): Permite excluir um tipo de despesa existente, buscando pelo nome e removendo-o da lista.

salvarTiposDespesaEmArquivo(): Salva os tipos de despesa em um arquivo de texto chamado "tiposdespesa.txt".

carregarTiposDespesaDeArquivo(): Carrega os tipos de despesa a partir do arquivo "tiposdespesa.txt".
*
*
*
*
*
*
*
*
Classe Util
A classe Util fornece métodos utilitários para a leitura de linhas de um arquivo. O método lerLinhasArquivo(File arquivo) lê as linhas do arquivo passado como parâmetro e retorna uma lista de strings contendo as linhas lidas.

Essas classes são essenciais para o gerenciamento dos tipos de despesa, permitindo a criação, listagem e exclusão desses tipos, além de salvar e carregar as informações dos tipos de despesa de/arquivo. 
*
*
*
*
*
*
*
*
Classe GerenciadorUsuarios
A classe GerenciadorUsuarios é responsável por gerenciar os usuários do sistema de controle de despesas, incluindo operações como cadastro, edição, exclusão, listagem e autenticação.

Atributos:

usuarios: Uma lista que armazena os objetos da classe Usuario.
USUARIOS_ARQUIVO: O nome do arquivo onde os dados dos usuários são salvos.
Construtor:

GerenciadorUsuarios(): Carrega os usuários do arquivo ao instanciar a classe.
Métodos:

cadastrarUsuario(): Permite cadastrar um novo usuário, solicitando nome de usuário e senha.

editarUsuario(): Permite editar o nome ou a senha de um usuário existente.

excluirUsuario(): Permite excluir um usuário existente após confirmação da senha.

listarUsuarios(): Lista todos os usuários cadastrados no sistema.

exibirMenuGerenciarUsuarios(): Exibe um menu de opções para gerenciar os usuários e chama os métodos correspondentes.

salvarUsuariosEmArquivo(): Salva os usuários no arquivo "usuarios.txt" usando serialização.

carregarUsuariosDeArquivo(): Carrega os usuários a partir do arquivo "usuarios.txt" usando desserialização.

autenticarUsuario(String username, String senha): Verifica se um usuário com o nome de usuário e senha fornecidos existe e retorna true se a autenticação for bem-sucedida.

A classe GerenciadorUsuarios é fundamental para o controle e gerenciamento dos usuários do sistema, permitindo que sejam cadastrados, editados, excluídos e autenticados, além de fornecer uma função para listar os usuários cadastrados.
