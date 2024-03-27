package com.abloom.mery.presentation.ui.category

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.abloom.mery.R
import com.abloom.mery.categorytest.CategoryViewModel
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.FragmentCategoryBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()
    private var isLogin = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel.isCheckLogin()
        setupIsLogin()
        setCategoryAdapter()
        observeCategory()
        setupSelectedTabItem(args.category)
        setupListener()
    }

    private fun setupIsLogin() {
        categoryViewModel.isLogin.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    binding.clNologin.visibility = View.INVISIBLE
                    isLogin = it
                }

                false -> {
                    binding.clNologin.visibility = View.VISIBLE
                    binding.tvLoginTag.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                    isLogin = it
                }
            }
        }
    }

    private fun setupSelectedTabItem(categoryArgs: CategoryArgs) {

        when (categoryArgs) {
            CategoryArgs.FINANCE ->  selectTabItem(CategoryArgs.FINANCE.categoryName, FINANCE_TABITEM_POSION)
            CategoryArgs.COMMUNICATION -> selectTabItem(CategoryArgs.COMMUNICATION.categoryName, COMMUNICATION_TABITEM_POSION)
            CategoryArgs.VALUES -> selectTabItem(CategoryArgs.VALUES.categoryName, VALUES_TABITEM_POSION)
            CategoryArgs.LIFESTYLE -> selectTabItem(CategoryArgs.LIFESTYLE.categoryName, LIFESTYLE_TABITEM_POSION)
            CategoryArgs.CHILDREN -> selectTabItem(CategoryArgs.CHILDREN.categoryName, CHILD_TABITEM_POSION)
            CategoryArgs.FAMILY -> selectTabItem(CategoryArgs.FAMILY.categoryName, FAMILY_TABITEM_POSION)
            CategoryArgs.SEX -> selectTabItem(CategoryArgs.SEX.categoryName, SEX_TABITEM_POSION)
            CategoryArgs.HEALTH -> selectTabItem(CategoryArgs.HEALTH.categoryName, HEALTH_TABITEM_POSION)
            CategoryArgs.WEDDING -> selectTabItem(CategoryArgs.WEDDING.categoryName, WEDDING_TABITEM_POSION)
            CategoryArgs.FUTURE -> selectTabItem(CategoryArgs.FUTURE.categoryName, FUTURE_TABITEM_POSION)
            CategoryArgs.PRESENT -> selectTabItem(CategoryArgs.PRESENT.categoryName, PRESENT_TABITEM_POSION)
            CategoryArgs.PAST -> selectTabItem(CategoryArgs.PAST.categoryName, PAST_TABITEM_POSION)
        }
    }

    private fun setupListener() {

        binding.appbarCategory.setOnNavigationClick {
            findNavController().popBackStack()
        }


        binding.tvLoginTag.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, false)
        }

        binding.tb.onTabSelected {
            when (it.position) {
                FINANCE_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("finance")
                }

                COMMUNICATION_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("communication")
                }

                VALUES_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("values")
                }

                LIFESTYLE_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("lifestyle")
                }

                CHILD_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("child")
                }

                FAMILY_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("family")
                }

                SEX_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("sex")
                }

                HEALTH_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("health")
                }

                WEDDING_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("wedding")
                }

                FUTURE_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("future")
                }

                PRESENT_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("present")
                }

                PAST_TABITEM_POSION -> {
                    categoryViewModel.requestQuestion("past")
                }
            }
        }
    }

    private fun observeCategory() {
        categoryViewModel.questions.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it)
        }
    }

    private fun setCategoryAdapter() {
        categoryAdapter = CategoryAdapter(::onCategoryItemClick)
        val categoryManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rv.apply {
            layoutManager = categoryManager
            adapter = categoryAdapter
            scrollToPosition(0)
        }
    }

    private fun selectTabItem(category: String, index: Int) {
        binding.tb.selectTab(binding.tb.getTabAt(index))
        lifecycleScope.launch(Dispatchers.Main) {
            binding.tb.setScrollPosition(index, 0f, false)
        }
        categoryViewModel.requestQuestion(category)
    }

    private fun onCategoryItemClick(question: Question) {
        val action =
            CategoryFragmentDirections.actionGlobalWriteAnswerFragment(question.questionId)
        findNavController().navigate(action)
    }

    companion object {

        private const val FINANCE_TABITEM_POSION = 0
        private const val COMMUNICATION_TABITEM_POSION = 1
        private const val VALUES_TABITEM_POSION = 2
        private const val LIFESTYLE_TABITEM_POSION = 3
        private const val CHILD_TABITEM_POSION = 4
        private const val FAMILY_TABITEM_POSION = 5
        private const val SEX_TABITEM_POSION = 6
        private const val HEALTH_TABITEM_POSION = 7
        private const val WEDDING_TABITEM_POSION = 8
        private const val FUTURE_TABITEM_POSION = 9
        private const val PRESENT_TABITEM_POSION = 10
        private const val PAST_TABITEM_POSION = 11
    }
}
