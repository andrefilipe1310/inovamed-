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

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Dados do formulario enviados:", formData);
    try {
      const response = await api.post("/clinical-representative", formData);
      console.log("Usuário registrado:", response.data);
    } catch (error) {
      console.error(
        "Erro ao registrar o usuário:",
        error.response?.data || error
      );
    }
  };

  if (userType === "representative") {
    return (
      <div className="form-container">
        <div className="logo-container">
          <img src="../../../../public/logo_branca.svg" alt="logo" />
        </div>
        <h1 className="title-page" style={{ marginTop: "0px" }}>
          CADASTRO
        </h1>
        <div className="form-header">
          <p>Representante Institucional</p>
          <button className="change-user">
            <Link to={"/cadastro"} className="ajust-link">
              Trocar
            </Link>
          </button>
        </div>
        <form className="form" onSubmit={handleSubmit}>
          <label htmlFor="name">
            Nome completo <i>Obrigatório</i>
          </label>
          <input
            type="text"
            id="name"
            name="name"
            required
            value={formData.name}
            onChange={handleChange}
          />

          <label htmlFor="email">
            Email corporativo <i>Obrigatório</i>
          </label>
          <input
            type="email"
            id="email"
            name="email"
            required
            value={formData.email}
            onChange={handleChange}
          />

          <label htmlFor="institution">
            Instituição <i>Obrigatório</i>
          </label>
          <input
            type="text"
            id="institution"
            name="institution"
            required
            value={formData.clinicalRole}
            onChange={handleChange}
          />

          <div className="phone-number">
            <label htmlFor="phone">
              N° para contato <i>Obrigatório</i>
            </label>
            <InputMask
              mask="(99)99999-9999"
              required
              value={formData.phone}
              onChange={(e) =>
                setFormData({ ...formData, phone: e.target.value })
              }
            >
              {(inputProps) => (
                <input {...inputProps} type="tel" id="phone" name="phone" />
              )}
            </InputMask>
          </div>

          <label htmlFor="clinicalRole">
            Cargo/função <i>Obrigatório</i>
          </label>
          <input
            type="text"
            id="clinicalRole"
            name="clinicalRole"
            required
            value={formData.clinicalRole}
            onChange={handleChange}
          />

          <label htmlFor="experience">
            Experiências/qualificações <i>Obrigatório</i>
          </label>
          <textarea
            id="experience"
            name="experience"
            rows={4}
            value={formData.experience}
            onChange={handleChange}
          />

          <div className="ajust-section">
            <div>
              <label htmlFor="password">
                Criar senha <i>Obrigatório</i>
              </label>
              <input
                type={showPassword ? "text" : "password"}
                id="password"
                name="password"
                required
                value={formData.password}
                onChange={handleChange}
              />
            </div>
            <button type="button" onClick={togglePasswordVisibility}>
              Ver
            </button>
          </div>

          <button type="submit" className="submit">
            FAZER CADASTRO
          </button>
        </form>
      </div>
    );
  } else {
    return <div>AINDA EM PRODUÇÃO!!</div>;
  }
}
