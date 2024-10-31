import { useState } from "react"
import { useSearchParams } from "react-router-dom"

export default function RegisterUser(){

    const [seachParams] = useSearchParams()
    const userType = seachParams.get('userType')

    if (userType === "representative"){
        const [formData, setFormData] = useState({
            nome: '',
            email: '',
            instituicao: '',
            contato: '',
            cargo: '',
            experiencias: '',
            senha: ''
          });
        
          const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
            const { name, value } = e.target;
            setFormData(prevState => ({
              ...prevState,
              [name]: value
            }));
          };
        
          const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
            e.preventDefault();
            console.log(formData);
            // Aqui você pode adicionar a lógica de envio do formulário
          };
    return(
        <div className="form-container">
      <div className="form-header">
        <button>Representante Institucional</button>
        <button>Trocar</button>
      </div>
      <form onSubmit={handleSubmit}>
        <label htmlFor="nome">Nome completo *</label>
        <input
          type="text"
          id="nome"
          name="nome"
          value={formData.nome}
          onChange={handleChange}
          required
        />

        <label htmlFor="email">Email corporativo *</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />

        <label htmlFor="instituicao">Instituição *</label>
        <input
          type="text"
          id="instituicao"
          name="instituicao"
          value={formData.instituicao}
          onChange={handleChange}
          required
        />

        <label htmlFor="contato">N° para contato *</label>
        <input
          type="text"
          id="contato"
          name="contato"
          value={formData.contato}
          onChange={handleChange}
          required
        />

        <label htmlFor="cargo">Cargo/função *</label>
        <input
          type="text"
          id="cargo"
          name="cargo"
          value={formData.cargo}
          onChange={handleChange}
          required
        />

        <label htmlFor="experiencias">Experiências/qualificações</label>
        <textarea
          id="experiencias"
          name="experiencias"
          value={formData.experiencias}
          onChange={handleChange}
          rows={4}
        />

        <label htmlFor="senha">Criar senha *</label>
        <input
          type="password"
          id="senha"
          name="senha"
          value={formData.senha}
          onChange={handleChange}
          required
        />

        <button type="submit">Fazer cadastro</button>
      </form>
    </div>
    )
}
    else{
        return(
            <div>AINDA EM PRODUÇÃO!!</div>
        )
    }

}