import { FormEvent, useState } from "react";
import { Link,useNavigate } from "react-router-dom";
import InputMask from "react-input-mask";

import apiUnauthorized from "../../../config/axiosUnauthorizedConfig";
import { PatientRequestDTO } from "../../../types/PatientTypes";

export default function ResgisterPatient() {
    const navigate = useNavigate()
    const [errorMessage, setErrorMessage] = useState<string | null>(null)
    const [showPassword, setShowPassword] = useState(false);
    const [formData, setFormData] = useState<PatientRequestDTO>({
        name: "",
        gender: "",
        birth: "",
        email: "",
        phone: "",
        password: "",
        roles: "PATIENT",
        medicalHistory: {},
        doctorKey: ""

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

        apiUnauthorized.post("/patient", formData)
            .then(response => {
                console.log(response.data)
                navigate("/login")

            }).catch(error => {
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
        <div className="form-container pac">
        <div className="logo-container-pac">
            <img src="../../../../public/logo_branca.svg" alt="logo" />
        </div>
        <h1 className="title-page-pac" style={{ marginTop: "0px" }}>CADASTRO</h1>
        <div className="form-pac">
            <p>{"Paciente"}</p>
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
                <label htmlFor="gender">
                    Gênero <i>Obrigatório</i>
                </label>
                <select
                    id="gender"
                    name="gender"
                    required
                    value={formData.gender}
                    onChange={handleChange}
                >
                    <option value="">Selecione</option>
                    <option value="masculino">Masculino</option>
                    <option value="feminino">Feminino</option>
                    <option value="outro">Outro</option>
                </select>
    
                <label htmlFor="birthDate">
                    Data de Nascimento <i>Obrigatório</i>
                </label>
                <input
                    type="date"
                    id="birthDate"
                    name="birth"
                    required
                    value={formData.birth}
                    onChange={handleChange}
                />
    
                <label htmlFor="doctorKey">
                    chave do Médico Responsável <i>Obrigatório</i>
                </label>
                <input
                    type="text"
                    id="doctorKey"
                    name="doctorKey"
                    required
                    value={formData.doctorKey}
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
            <button type="submit" className="submit pac">
                FAZER CADASTRO
            </button>
        </form>
    </div>
     );
    }
