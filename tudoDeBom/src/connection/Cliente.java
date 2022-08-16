public class Cliente implements  IClientee<Long> {
    private long id;
    private String nome;
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
	public String toJson() {
		String json = "{";
		json += " \"id\": " + this.id + ",";
		json += " \"nome\": " +  "\"" + this.nome + "\"" + ",";		
		json += "}";
		return json;
	}
	
	public void toObject(String json) {
		try {
			json = json.replaceAll("\\{", "");
			json = json.replaceAll("\\}", "");
						
			String[] registros = json.split(",");
			
			//registros[0]
			//"id": 0

			//registros[1]
			//"nome": "testando spark"

			//registros[2]
			//"email": "spark@gmail.com"
				
			// CAMPO ID
			String[] campoId = registros[0].split(":");
			//campoId[0]="id"
			//campoId[1]=0
			String id = campoId[1].replace("\"","").trim();
			this.setId(Long.parseLong(id));
			
			// CAMPO NOME
			String[] campoNome = registros[1].split(":");
			//campoNome[0]="nome"
			//campoNome[1]="testando spark"
			String nome = campoNome[1].replace("\"","").trim();
			this.setNome(nome);
			
			// CAMPO EMAIL
			String[] campoEmail = registros[2].split(":");
		
			String email = campoEmail[1].replace("\"","").trim();
			this.setEmail(email);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.valueOf(this.id) + this.nome + this.cpf;
	}

}