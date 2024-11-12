import { useState } from "react";
import { Link, useSearchParams } from "react-router-dom";
import InputMask from "react-input-mask";
import api from "../../../config/axiosConfig";

export default function RegisterUser() {
  const [searchParams] = useSearchParams();
  const userType = searchParams.get("userType");

  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    id: null,
    name: "",
    email: "",
    password: "",
    roles: "REPRESENTATIVE",
    phone: "",
    clinicalRole: "",
    experience: "",
  });

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  if (userType === "representative") {
    return (
      <div className="form-container">
        <div className="logo-container"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
        <h1 className="title-page" style={{ marginTop: "0px" }}>CADASTRO</h1>
        <div className="form-header">
          <p>Representante Institucional</p>
          <button className="change-user"><Link to={"/cadastro"} className="ajust-link">Trocar</Link></button>
        </div>
        <div className="form">
          <label htmlFor="nome">Nome completo <i>Obrigatório</i></label>
          <input type="text" id="name" name="name" required />

          <label htmlFor="email">Email corporativo <i>Obrigatório</i></label>
          <input type="email" id="email" name="email" required />

          <label htmlFor="instituicao">Instituição <i>Obrigatório</i></label>
          <input type="text" id="institution" name="institution" required />

          <div className="phone-number">
            <label htmlFor="contato">N° para contato <i>Obrigatório</i></label>
            <InputMask mask="(99)99999-9999" required>
              {(inputProps) => <input {...inputProps} type="tel" />}
            </InputMask>
          </div>

          <label htmlFor="cargo">Cargo/função <i>Obrigatório</i></label>
          <input type="text" id="function" name="function" required />

          <label htmlFor="experiencias">Experiências/qualificações <i>Obrigatório</i></label>
          <textarea id="exp" name="exp" rows={4} />

          <div className="ajust-section">
            <div>
              <label htmlFor="senha">Criar senha <i>Obrigatório</i></label>
              <input type={showPassword ? 'text' : 'password'} id="password" name="password" required />
            </div>
            <button onClick={togglePasswordVisibility}>Mostrar senha</button>
          </div>

          <button type="submit" className="submit">FAZER CADASTRO</button>
        </div>
      </div>
    );
  } else if (userType === "medic") {
    return (
      <div className="form-container">
        <div className="logo-container"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
        <h1 className="title-page" style={{ marginTop: "0px" }}>CADASTRO</h1>
        <div className="form-header">
          <p>Médico Responsável</p>
          <button className="change-user"><Link to={"/cadastro"} className="ajust-link">Trocar</Link></button>
        </div>
        <div className="form">
          <label htmlFor="nome">Nome completo <i>Obrigatório</i></label>
          <input type="text" id="name" name="name" required />

          <label htmlFor="email">Email corporativo <i>Obrigatório</i></label>
          <input type="email" id="email" name="email" required />

          <label htmlFor="instituicao">Instituição/ Clínica / Hospital<i>Obrigatório</i></label>
          <input type="text" id="institution" name="institution" required />

          <div className="phone-number">
            <label htmlFor="contato">N° para contato <i>Obrigatório</i></label>
            <InputMask mask="(99)99999-9999" required>
              {(inputProps) => <input {...inputProps} type="tel" />}
            </InputMask>
          </div>

          <label htmlFor="cargo">Especialidade médica <i>Obrigatório</i></label>
          <input type="text" id="especialidade" name="especialidade" required />

          <label htmlFor="cargo">CRM ou registro equivalente <i>Obrigatório</i></label>
          <input type="number" id="CRM" name="CRM" required />

          <label htmlFor="experiencias">Experiência em pesquisa clínica <i>Obrigatório</i></label>
          <textarea id="exp" name="exp" rows={4} />

          <div className="ajust-section">
            <div>
              <label htmlFor="senha">Criar senha <i>Obrigatório</i></label>
              <input type={showPassword ? 'text' : 'password'} id="password" name="password" required />
            </div>
            <button onClick={togglePasswordVisibility}>Mostrar senha</button>
          </div>

          <button type="submit" className="submit">FAZER CADASTRO</button>
        </div>
      </div>
    );
  } else if (userType === "pacient") {
    return (
      <div className="form-container">
        <div className="logo-container-pac"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
        <h1 className="title-page-pac " style={{ marginTop: "0px" }}>CADASTRO</h1>
        <div className="form-header">
          <p>Paciente</p>
          <button className="change-user"><Link to={"/cadastro"} className="ajust-link">Trocar</Link></button>
        </div>
        <div className="form">
          <label htmlFor="nome">Nome completo <i>Obrigatório</i></label>
          <input type="text" id="name" name="name" required />

          <label htmlFor="email">Email <i>Obrigatório</i></label>
          <input type="email" id="email" name="email" required />

          <label htmlFor="genero">Gênero <i>Obrigatório</i></label>
          <select id="gender" name="gender" required>
            <option value="">Selecione</option>
            <option value="masculino">Masculino</option>
            <option value="feminino">Feminino</option>
            <option value="outro">Outro</option>
          </select>

          <label htmlFor="dataNascimento">Data de Nascimento <i>Obrigatório</i></label>
          <input type="date" id="dataNascimento" name="dataNascimento" required />

          <div className="phone-number">
            <label htmlFor="contato">N° para contato <i>Obrigatório</i></label>
            <InputMask mask="(99)99999-9999" required>
              {(inputProps) => <input {...inputProps} type="tel" />}
            </InputMask>
          </div>

          <label htmlFor="cargo">CRM do Médico Responsável <i>Obrigatório</i></label>
          <input type="number" id="CRM" name="CRM" required />

          <div className="ajust-section">
            <div>
              <label htmlFor="senha">Criar senha <i>Obrigatório</i></label>
              <input type={showPassword ? 'text' : 'password'} id="password" name="password" required />
            </div>
            <button onClick={togglePasswordVisibility}>Mostrar senha</button>
          </div>

          <button type="submit" className="submit">FAZER CADASTRO</button>
        </div>
      </div>
    );
  }
}
