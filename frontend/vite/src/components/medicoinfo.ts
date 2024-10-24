export interface Pesquisamedico {
    pacientenum: string;
    dataini: string;
    datafim: string;
    desc: string;
    fase: string;
  }
  const pesquisamedico: Pesquisamedico[] =[
    {
      pacientenum: "Paciente: 1",
      dataini: "Data de inicio: 27/09/2024",
      datafim: "Data Conclusão: 30/11/2024",
      desc: "Esta pesquisa clínica avalia a eficácia de um novo medicamento em pacientes com Alzheimer em estágios iniciais. Com 200 participantes, o estudo acompanhará mudanças na função cognitiva e na qualidade de vida ao longo de 12 meses. Os resultados visam identificar benefícios e efeitos colaterais, contribuindo para novas terapias. ",
      fase: "Esta pesquisa clínica avalia a eficácia de um novo medicamento em pacientes com Alzheimer em estágios iniciais. Com 200 participantes, o estudo acompanhará mudanças na função cognitiva e na qualidade de vida ao longo de 12 meses. Os resultados visam identificar benefícios e efeitos colaterais, contribuindo para novas terapias. "
    }
]
export default pesquisamedico
   