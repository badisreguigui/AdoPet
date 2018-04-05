<?php

namespace VetsBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Tarif
 *
 * @ORM\Table(name="tarif")
 * @ORM\Entity
 */
class Tarif
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_veto", type="integer", nullable=false)
     */
    private $idVeto;

    /**
     * @var integer
     *
     * @ORM\Column(name="consultation", type="integer", nullable=false)
     */
    private $consultation;

    /**
     * @var integer
     *
     * @ORM\Column(name="vaccinationChat", type="integer", nullable=false)
     */
    private $vaccinationchat;

    /**
     * @var integer
     *
     * @ORM\Column(name="vaccinationChien", type="integer", nullable=false)
     */
    private $vaccinationchien;

    /**
     * @var integer
     *
     * @ORM\Column(name="sterilisation", type="integer", nullable=false)
     */
    private $sterilisation;

    /**
     * @var integer
     *
     * @ORM\Column(name="analyses", type="integer", nullable=false)
     */
    private $analyses;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_tarif", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idTarif;

    /**
     * @return int
     */
    public function getIdVeto()
    {
        return $this->idVeto;
    }

    /**
     * @param int $idVeto
     */
    public function setIdVeto($idVeto)
    {
        $this->idVeto = $idVeto;
    }

    /**
     * @return int
     */
    public function getConsultation()
    {
        return $this->consultation;
    }

    /**
     * @param int $consultation
     */
    public function setConsultation($consultation)
    {
        $this->consultation = $consultation;
    }

    /**
     * @return int
     */
    public function getVaccinationchat()
    {
        return $this->vaccinationchat;
    }

    /**
     * @param int $vaccinationchat
     */
    public function setVaccinationchat($vaccinationchat)
    {
        $this->vaccinationchat = $vaccinationchat;
    }

    /**
     * @return int
     */
    public function getVaccinationchien()
    {
        return $this->vaccinationchien;
    }

    /**
     * @param int $vaccinationchien
     */
    public function setVaccinationchien($vaccinationchien)
    {
        $this->vaccinationchien = $vaccinationchien;
    }

    /**
     * @return int
     */
    public function getSterilisation()
    {
        return $this->sterilisation;
    }

    /**
     * @param int $sterilisation
     */
    public function setSterilisation($sterilisation)
    {
        $this->sterilisation = $sterilisation;
    }

    /**
     * @return int
     */
    public function getAnalyses()
    {
        return $this->analyses;
    }

    /**
     * @param int $analyses
     */
    public function setAnalyses($analyses)
    {
        $this->analyses = $analyses;
    }

    /**
     * @return int
     */
    public function getIdTarif()
    {
        return $this->idTarif;
    }

    /**
     * @param int $idTarif
     */
    public function setIdTarif($idTarif)
    {
        $this->idTarif = $idTarif;
    }


}

