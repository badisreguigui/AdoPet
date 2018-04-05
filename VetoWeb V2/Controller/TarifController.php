<?php

namespace VetsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use VetsBundle\Entity\Tarif;
use VetsBundle\Entity\TestVet;

class TarifController extends Controller
{
    public function BudgetAction(Request $request)
    {
        $consultation=$request->get('consultation');
        $chat=$request->get('vaccinationchat');
        $chien=$request->get('vaccinationchien');
        $ster=$request->get('sterilisation');
        $analyse=$request->get('analyses');
        $em=$this->getDoctrine()->getManager();
        $idveto=$request->get('idvet');
        $budget=$em->getRepository(TestVet::class)->findBudget($idveto);
        if($request->isMethod('post')) {
            $total=$consultation+$chat+$chien+$ster+$analyse;
            return $this->render('VetsBundle:Default:index.html.twig', array('total'=>$total));
        }
        return $this->render('VetsBundle:Tarif:list_tarif.html.twig', array('budget'=>$budget));

    }

    public function NewTarifAction(Request $request)
    {
        $Tarif=new Tarif();
        if($request->isMethod('POST'))
        {
            $em=$this->getDoctrine()->getManager();
            $Tarif->setIdVeto($request->get('id_veto'));
            $Tarif->setAnalyses($request->get('analyses'));
            $Tarif->setConsultation($request->get('consultation'));
            $Tarif->setVaccinationchat($request->get('vaccinationChat'));
            $Tarif->setVaccinationchien($request->get('vaccinationChien'));
            $Tarif->setSterilisation($request->get('sterilisation'));
            $em->persist($Tarif);
            $em->flush();

            return $this->redirectToRoute('list');



        }
        return $this->render('VetsBundle:Tarif:newTarif.html.twig');
    }


}
