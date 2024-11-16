import {  useState } from "react";
import { Link } from "react-router-dom";

export default function RedirectUserType(){

    const [idUser,setIdUser] = useState("")

    return(
        <div style={{display:"flex", flexDirection:"column", alignItems:"center", paddingTop:"2vw"}}>
            <div className="logo-container"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
            <h1 className="title-page">CADASTRO</h1>
            <div className="card-border">
                <div className="container-user-type-select">
                    <h3>SEJA BEM VINDO!</h3>
                    <p>A inovamed é uma plataforma que ajuda a conectar estudos clínico-científicos e pacientes.Ao realizar o cadastro você estará contribuindo com os avanços médicos além de se manter informado sobre os processos e as novas opções de tratamento e cuidados.</p>
                    <p>Para iniciar o cadastro, primeiramente precisamos que você selecione se você deseja participar como Representante, Médico responsável ou Paciente:</p>
                    <select name="userType" id="" onChange={(e)=>setIdUser(e.target.value)}>
                        <option value="" disabled selected>INFORME O TIPO DE USUÁRIO</option>
                        <option value="paciente">Paciente</option>
                        <option value="medico">Médico responsável</option>
                        <option value="representante"> Representante de estudo</option>
                    </select>
                </div>
            </div>
        
            <Link to={idUser ? `/cadastro/${idUser}` : ""} className="link-to-register">CONFIRMAR</Link>
        </div>
    )
}