import { FormEvent, useState } from "react";
import Navbar from "../../components/Navbar";
import api from "../../config/axiosConfig";
import { ResearchRequestDTO } from "../../types/ResearchTypes";
import { Navigate, useNavigate } from "react-router-dom";

export default function RepNovaPesquisa() {
    const [file, setFile] = useState<File | null>(null); // Estado para o arquivo
    const navigate = useNavigate()

    const [researchRequestDTO, setResearchRequestDTO] = useState<ResearchRequestDTO>({
        title: "",
        area: "",
        numberOfPatients: 0,
        availableVacancies: 0,
        responsibleDoctors: [],
        institutions: [],
        description: "",
        criteria: { inclusion: [""], exclusion: [""] },
        start_date:"2024-01-01",
        end_date:"2025-12-31",
        phases: [{number:1,title:"primeira",description:"primeira fase"}],
        currentPhase: 0,
        location: "",
     
    });

    const handleCreateResearch = async () => {

        
        //researchRequestDTO.criteria = JSON.stringify(researchRequestDTO.criteria)
        const formData = new FormData();
        formData.append("title",researchRequestDTO.title)
        formData.append("area",researchRequestDTO.area)
        formData.append("numberOfPatients",String(researchRequestDTO.numberOfPatients))
        formData.append("availableVacancies",String(researchRequestDTO.availableVacancies))
        formData.append("availableVacancies",String(researchRequestDTO.availableVacancies))
        researchRequestDTO.responsibleDoctors.map((responsibleDoctor:string)=>{
            formData.append("responsibleDoctors",responsibleDoctor)
        })
        researchRequestDTO.institutions.map((institution:string)=>{
            formData.append("institutions",institution)
        })
        formData.append("criteria",JSON.stringify(researchRequestDTO.criteria))
        formData.append("start_date",researchRequestDTO.start_date)
        formData.append("end_date",researchRequestDTO.end_date)
        formData.append("phases",JSON.stringify(researchRequestDTO.phases))
        formData.append("currentPhase",String(researchRequestDTO.currentPhase))
        formData.append("location",researchRequestDTO.location)
        console.log(formData.get("phases"))
       
        if (file) {
            formData.append("file", file); // PDF
        }
        
        api.post("/research", formData,{headers: {
            "Content-Type": "multipart/form-data",
        },})
            .then(response => {
                navigate("/representante/listapesquisas")
            })
            .catch(error => {
                console.error(error);
               
            });
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        handleCreateResearch();
    };

    const handleChange = (field: string, value: any) => {
        setResearchRequestDTO(prev => ({ ...prev, [field]: value }));
    };

    const handleArrayChange = (field: string, index: number, value: string, isSubfield = false) => {
        setResearchRequestDTO(prev => {
            const updatedArray = [...(isSubfield ? prev.criteria[field] : prev[field])];
            updatedArray[index] = value;
            return isSubfield
                ? { ...prev, criteria: { ...prev.criteria, [field]: updatedArray } }
                : { ...prev, [field]: updatedArray };
        });
    };

    const handleAddField = (field: string, isSubfield = false) => {
        setResearchRequestDTO(prev => {
            const updatedArray = [...(isSubfield ? prev.criteria[field] : prev[field]), ""];
            return isSubfield
                ? { ...prev, criteria: { ...prev.criteria, [field]: updatedArray } }
                : { ...prev, [field]: updatedArray };
        });
    };

    

    return (
        <>
            <Navbar />
            <div className='container-page'>
                <h1 className="title-page">NOVA PESQUISA</h1>
                <div className="card-border" style={{ marginBottom: "2vw" }}>
                    <form onSubmit={handleSubmit} className='form-new-research'>
                        <div className='session'>
                            <label htmlFor="title">Título</label>
                            <input
                                type="text"
                                name='title'
                                onChange={(e) => handleChange("title", e.target.value)}
                            />
                        </div>

                        <div className='session'>
                            <div>
                                <label htmlFor="numberOfPatients">Pacientes</label>
                                <input
                                    type='number'
                                    name='numberOfPatients'
                                    onChange={(e) => handleChange("numberOfPatients", +e.target.value)}
                                />
                            </div>
                            <div>
                                <label htmlFor="area">Área do estudo</label>
                                <input
                                    type="text"
                                    name='area'
                                    onChange={(e) => handleChange("area", e.target.value)}
                                />
                            </div>
                        </div>

                        <div className='session'>
                            <label htmlFor="responsibleDoctors">Médico responsável</label>
                            {researchRequestDTO.responsibleDoctors.map((doctor, index) => (
                                <input
                                    key={index}
                                    type='text'
                                    name='responsibleDoctors'
                                    value={doctor}
                                    onChange={(e) => handleArrayChange("responsibleDoctors", index, e.target.value)}
                                />
                            ))}
                            <button type="button" onClick={() => handleAddField("responsibleDoctors")}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="institutions">Instituição responsável</label>
                            {researchRequestDTO.institutions.map((institution, index) => (
                                <input
                                    key={index}
                                    type='text'
                                    name='institutions'
                                    value={institution}
                                    onChange={(e) => handleArrayChange("institutions", index, e.target.value)}
                                />
                            ))}
                            <button type="button" onClick={() => handleAddField("institutions")}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="criteria">Critérios de Inclusão e Exclusão</label>
                            <p>Inclusão:</p>
                            {researchRequestDTO.criteria.inclusion.map((criteria, index) => (
                                <input
                                    key={`inclusion-${index}`}
                                    type='text'
                                    name='includeCriteria'
                                    value={criteria}
                                    onChange={(e) => handleArrayChange("inclusion", index, e.target.value, true)}
                                />
                            ))}
                            <button type="button" onClick={() => handleAddField("inclusion", true)}>+</button>
                            <p>Exclusão:</p>
                            {researchRequestDTO.criteria.exclusion.map((criteria, index) => (
                                <input
                                    key={`exclusion-${index}`}
                                    type='text'
                                    name='excludeCriteria'
                                    value={criteria}
                                    onChange={(e) => handleArrayChange("exclusion", index, e.target.value, true)}
                                />
                            ))}
                            <button type="button" onClick={() => handleAddField("exclusion", true)}>+</button>
                        </div>

                        <div className='session'>
                            <label htmlFor="description">Descrição da pesquisa</label>
                            <textarea
                                name='description'
                                onChange={(e) => handleChange("description", e.target.value)}
                            />
                        </div>

                        <div className='session'>
                            <label htmlFor="studyDuration">Duração da pesquisa</label>
                            <div>
                                <label htmlFor="start">Data de início</label>
                                <input
                                    type='date'
                                    name='start'
                                    onChange={(e) => handleChange("studyDuration", { ...researchRequestDTO, start_date: e.target.value })}
                                />
                            </div>
                            <div>
                                <label htmlFor="end">Data de encerramento</label>
                                <input
                                    type='date'
                                    name='end'
                                    onChange={(e) => handleChange("studyDuration", { ...researchRequestDTO, end_date: e.target.value })}
                                />
                            </div>
                        </div>

                        <div className='session'>
                            <label htmlFor="location"> Local da pesquisa</label>
                            <input
                                type='text'
                                name='location'
                                onChange={(e) => handleChange("location", e.target.value)}
                            />
                        </div>

                         {/* Campo para upload do arquivo */}
                         <div className='session'>
                            <label htmlFor="file">Anexar PDF</label>
                            <input
                                type="file"
                                name="file"
                                accept=".pdf"
                                onChange={(e) => {
                                    if (e.target.files) {
                                        setFile(e.target.files[0]);
                                        
                                    }
                                }}
                            />
                        </div>

                        <button type="submit" className="send-data">CRIAR PESQUISA</button>
                    </form>
                </div>
            </div>
        </>
    );
}
