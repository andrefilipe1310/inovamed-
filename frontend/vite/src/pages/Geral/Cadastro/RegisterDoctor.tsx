import { FormEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import InputMask from "react-input-mask";

import apiUnauthorized from "../../../config/axiosUnauthorizedConfig";
import { DoctorRequestDTO } from "../../../types/DoctorTypes";

export default function RegisterDoctor() {
    const navigate = useNavigate()
    const [errorMessage, setErrorMessage] = useState<string | null>(null)
    const [showPassword, setShowPassword] = useState(false);
    const [formData, setFormData] = useState<DoctorRequestDTO>({
        name: "",
        email: "",
        doctorExperienceEnum:"NEVER",
        password: "",
        phone: "",
        clinic: "",
        roles: "DOCTOR",
        specialty:"",
        Crm:"",

    });

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
            apiUnauthorized.post("/doctor", formData)
                .then(response => {
                    console.log(response.data)
                    navigate("/login")

                }).catch(error => {
                    console.log(formData)
                    console.error("Erro ao registrar usuario:", error);
                    if (!error.response) {
                        setErrorMessage("O servidor está offline. Tente novamente mais tarde.");
                    } else if (error.response.data.statusCode == 'CONFLICT') {
                        setErrorMessage("Este usuario ja esta registrado.");
                    }
                    else {
                        setErrorMessage("Erro ao fazer registro. Preencha todos os campos obrigatorios.");
                    }
                });


        

    };

    return (
        <div className="form-container">
            <div className="logo-container">
                <img src="../../../../public/logo_branca.svg" alt="logo" />
            </div>
            <h1 className="title-page" style={{ marginTop: "0px" }}>CADASTRO</h1>
            <div className="form-header">
                <p>{"Médico Responsável"}</p>
                <button className="change-user">
                    <Link to={"/cadastro"} className="ajust-link">Trocar</Link>
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
                <>
                    <label htmlFor="clinic">
                     Clinica <i>Obrigatório</i>
                    </label>
                    <input
                        type="text"
                        id="clinic"
                        name="clinic"
                        required
                     value={formData.clinic}
                     onChange={handleChange}
                    />
                </>
                <div className="phone-number">
                    <label htmlFor="phone">
                        N° para contato <i>Obrigatório</i>
                    </label>
                    <InputMask
                        mask="(99)99999-9999"
                        required
                        value={formData.phone}
                        onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                    >
                        {(inputProps) => <input {...inputProps} type="tel" id="phone" name="phone" />}
                    </InputMask>
                </div>

                <>
                    <label htmlFor="specialty">
                        Especialidade médica <i>Obrigatório</i>
                    </label>
                    <input
                        type="text"
                        id="specialty"
                        name="specialty"
                        required
                         value={formData.specialty}
                        onChange={handleChange}
                    />

                    <label htmlFor="Crm">
                        CRM ou registro equivalente <i>Obrigatório</i>
                    </label>
                    <input
                        type="text"
                        id="Crm"
                        name="Crm"
                        required
                         value={formData.Crm}
                        onChange={handleChange}
                    />
                </>

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
                        {showPassword ? "Ocultar" : "Ver"}
                    </button>
                </div>
                {errorMessage && <p style={{ color: "red" }} className="error-message">{errorMessage}</p>}
                <button type="submit" className="submit">
                    FAZER CADASTRO
                </button>
            </form>
        </div>

    )
}