<?php

namespace VetsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use VetsBundle\Entity\Users;
use VetsBundle\Entity\test;

class VetsController extends Controller
{

    public function inscrireVetoAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();

        $Users=new Users();
        if($request->isMethod('POST'))
        {
            $Users->setNom($request->get('nom'));
            $Users->setPrenom($request->get('prenom'));
            $Users->setEmail($request->get('email'));
            $Users->setTelephone($request->get('telephone'));
            $Users->setRue($request->get('rue'));
            $Users->setVille($request->get('ville'));
            $Users->setGouvernorat($request->get('gouvernorat'));
            $Users->setCodepostal($request->get('CodePostal'));
            $Users->setImage($request->get('image'));
            $Users->setDescription($request->get('description'));
            $Users->setRole("veto");
            $Users->setLogin($request->get('login'));
            $Users->setPassword($request->get('password'));
            $em->persist($Users);
            $em->flush();
            return $this->render('VetsBundle::profilPerso.html.twig',array('users'=>$Users));



        }
        return $this->render('VetsBundle::newVeto.html.twig');
    }

    public function listAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $Users=$em->getRepository(Users::class)->findAll();

        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate(
            $Users,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',4)


        );
        return $this->render('VetsBundle::vets.html.twig', array('users'=>$result));

    }

    public function profilVetoAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $Users=$em->getRepository(Users::class)->find($id);
        $em->flush();
        return $this->render('VetsBundle::profil.html.twig',array('users'=>$Users));
    }

    public function monProfilAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $Users=$em->getRepository(Users::class)->find($id);
        $em->flush();
        return $this->render('VetsBundle::profilPerso.html.twig',array('users'=>$Users));
    }

    public function modifierAction(Request $request,$id)
    {


        $Users = $this->getDoctrine()->getRepository(Users::class)->find($id);
        if($request->isMethod('POST'))
        {
            $em=$this->getDoctrine()->getManager();
            $Users->setNom($request->get('nom'));
            $Users->setPrenom($request->get('prenom'));
            $Users->setEmail($request->get('email'));
            $Users->setTelephone($request->get('telephone'));
            $Users->setRue($request->get('rue'));
            $Users->setVille($request->get('ville'));
            $Users->setGouvernorat($request->get('gouvernorat'));
            $Users->setCodepostal($request->get('CodePostal'));
            $Users->setImage($request->get('image'));
            $Users->setDescription($request->get('description'));
            $Users->setRole("veto");
            $Users->setLogin($request->get('login'));
            $Users->setPassword($request->get('password'));
            $em->persist($Users);
            $em->flush();

        }


        return $this->render('VetsBundle::modifier.html.twig',array("users"=>$Users));
    }

    public function listBackAction()
    {
        $em=$this->getDoctrine()->getManager();
        $Users=$em->getRepository(Users::class)->findAll();
        return $this->render('VetsBundle:Back:admin.html.twig', array('users'=>$Users));

    }

    public function supprimerBackAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $Users=$em->getRepository(Users::class)->find($id);
        $em->remove($Users);
        $em->flush();
        return $this->redirectToRoute('listBack');
    }




}
