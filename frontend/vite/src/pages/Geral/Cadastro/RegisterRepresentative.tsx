import { FormEvent, useState } from "react";
import InputMask from "react-input-mask";
import { Link, useNavigate } from "react-router-dom";
import apiUnauthorized from "../../../config/axiosUnauthorizedConfig";


export default function RegisterRepresentative() {

    const navigate = useNavigate()

    const [errorMessage, setErrorMessage] = useState<string | null>(null)
    const [showPassword, setShowPassword] = useState(false);
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        phone: "",
        clinicalRole: "",
        roles: "STUDY_REPRESENTATIVE",
        experience: "",


    });

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        apiUnauthorized.post("/clinical-representative", formData)
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
    }
    return (
        <div className="form-container">
            <div className="logo-container">
                <img src="../../../../public/logo_branca.svg" alt="logo" />
            </div>
            <h1 className="title-page" style={{ marginTop: "0px" }}>CADASTRO</h1>
            <div className="form-header">
                <p>{"Representante Institucional"}</p>
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
                        mask="(99) 99999-9999"
                        value={formData.phone}
                        onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                        as={((inputProps: any) => <input {...inputProps} type="tel" id="phone" name="phone" />) as any}
                    />
                    <div className="">
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
                </div>
                </div>
                
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